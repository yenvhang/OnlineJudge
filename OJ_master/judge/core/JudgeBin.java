package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import top.yenvhang.model.Code;
import top.yenvhang.model.JudgeResult;
import top.yenvhang.model.Problem;
import top.yenvhang.model.RunInfo;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;
import util.IO_Utils;
import util.ShellUtil;



public class JudgeBin {
	private JudgeResult judgeResult =new JudgeResult();
	String source_package="/var/oj_code/sh";
	String source_file="Main.java";
	String err_log_file="err_log.txt";
	String javac_command =source_package+"/ojJavac.sh";
	String java_command=source_package+"/ojJava.sh";
	String clear_command=source_package+"/oj_clear.sh";
	String create_command=source_package+"/oj_create.sh";
	//比较两个文件时　忽略行尾的空格
	String diff_ignore_Line_command =source_package+"/oj_diff_ignore.sh";
	//两个文件存在不同的地方　则返回不同之处
	String diff_q_command =source_package+"/oj_diff_q.sh";
	User user;
	Problem problem;
	Submission submisson;
	Code code;
	RunInfo runInfo;
	ScheduledExecutorService service=
			Executors.newSingleThreadScheduledExecutor();
	public JudgeBin(User user,Problem problem,Submission submission,Code code,RunInfo runInfo) {
		
		this.user=user;
		this.problem=problem;
		this.submisson=submission;
		this.code=code;
		this.runInfo=runInfo;
	}

	public void process() {
		
		//获取题目信息　如果题目为空　返回　未知原因！
		if(problem==null){
			judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
			submisson.setJudgeResult(judgeResult);
			return;
		}
		
		//提交次数+1;
		user.setSubmitted(user.getSubmitted()+1);
		problem.setSubmitted(problem.getSubmitted()+1);
		//获取提交代码 如果代码为空　返回　编译错误
		
		if(code==null||code.getCode().trim().isEmpty()){
			judgeResult.setJudgeResultName(JudgeResult.COMPILE_FAILED);
			runInfo.setRunInfo("代码为空");
			submisson.setJudgeResult(judgeResult);
			return ;
		}
		//创建用户提交代码所需目录
		try {
			Process create =Runtime.getRuntime().exec(create_command+
					" "+problem.getProblem_id()+
					" "+user.getUser_id());
//			System.out.println(IO_Utils.getString(create.getErrorStream()));
			create.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		//将代码写入文件
		writeCode2File(code.getCode());
		
		try {
			//编译
			Process compile =Runtime.getRuntime().exec(javac_command+	" "+problem.getProblem_id()+" "+user.getUser_id());
			compile.waitFor();
			InputStream err_ins=compile.getErrorStream();
			//编译错误
			if(err_ins!=null){
				String value =IO_Utils.getString(err_ins);
				if(value!=null&&!value.trim().isEmpty()){
					judgeResult.setJudgeResultName(JudgeResult.COMPILE_FAILED);
					runInfo.setRunInfo(value); 
					return ;
				}
			}
			
			judgeResult.setJudgeResultName(JudgeResult.COMPILE_SUCCEED);
			//开始运行程序
			judgeResult.setJudgeResultName(JudgeResult.QUEUING);
			submisson.setExecuteTime(new Date());
			long startTime =System.currentTimeMillis();
			//计时开始
			final Process run =Runtime.getRuntime().exec(java_command+
					" "+problem.getProblem_id()+
					" "+user.getUser_id());
			service.schedule(new Runnable() {

				public void run() {
					run.destroy();
					System.out.println("中断");
				}
			},problem.getTime_limit(), TimeUnit.SECONDS);
			run.waitFor();
			long endTime =System.currentTimeMillis();
			//如果程序在规定时间内运行结束。则中断　计时线程
			if(run.isAlive()){
				if(!ShellUtil.isRunOK(run.getErrorStream())){
					judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
					return;
				}
			}
			service.shutdownNow();
			//调用shell 出错
			//运行程序出错
			String callback =IO_Utils.getString(new FileInputStream(new File("/var/oj_code/text",
					problem.getProblem_id()+"/"+user.getUser_id()+"/"+err_log_file)));
				if(callback!=null&&callback.trim().length()>0){
					//TODO 输出到日志文件中
					System.out.println(callback);
					judgeResult.setJudgeResultName(JudgeResult.RUNTIM_EERROR);
					runInfo.setRunInfo(callback);
					return;
				}
			//超时
			if(endTime-startTime>=problem.getTime_limit()){
				judgeResult.setJudgeResultName(JudgeResult.RUNTIME_LIMIT);
				System.out.println("time:"+(endTime-startTime));
				System.out.println(user.getUser_id()+" :"+judgeResult.getJudgeResultName());
				return;
			}
			//忽略空格　判断输出结果是否相同
			Process diff_ignore =Runtime.getRuntime().exec(diff_ignore_Line_command+
					" "+problem.getProblem_id()+
					" "+user.getUser_id());
			diff_ignore.waitFor();
			String msg =IO_Utils.getString(diff_ignore.getInputStream());
			//输出结果不相同
			if(msg!=null&&msg.length()>0){
				judgeResult.setJudgeResultName(JudgeResult.WRONG_ANSWER);
				runInfo.setRunInfo(msg);
				return ;
			}
			//输出结果相同　判断格式是否相同
			Process diff_q =Runtime.getRuntime().exec(diff_q_command+
					" "+problem.getProblem_id()+
					" "+user.getUser_id());
			diff_q.waitFor();
			String msg2 =IO_Utils.getString(diff_q.getInputStream());
			//如果格式不相同
			if(msg2!=null&&msg.length()>0){
				judgeResult.setJudgeResultName(JudgeResult.PRESENTATION_ERROR);
				runInfo.setRunInfo(msg2);
				return;
			}
			//输出结果相同
			judgeResult.setJudgeResultName(JudgeResult.AC);
			submisson.setUseTime(endTime-startTime);
			System.out.println(user.getUser_id()+": "+"runtime:"+(endTime-startTime));
			System.out.println(judgeResult.getJudgeResultName());
			//更新　AC 次数
			//TODO 重复AC 同一个题不能重复+1;
			user.setSolved(user.getSolved()+1);
			user.setScore(problem.getScore()+user.getScore());
			problem.setSolved(problem.getSolved()+1);
		
			
			
		} catch (IOException e) {
			judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
			e.printStackTrace();
		} catch (InterruptedException e) {
			judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
			e.printStackTrace();
		}
		finally {
			clearTemp();
			submisson.setJudgeResult(judgeResult);
		}
	}
	public void writeCode2File(String code){
		File file =new File("/var/oj_code/text"+
				"/"+problem.getProblem_id()+
				"/"+user.getUser_id(),source_file);
		BufferedReader reader = null;
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter(file));
			write.write(code);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
		}
		finally {
			try {
				if(reader!=null){
					reader.close();
				} 
				if(write!=null){
					write.close();
				}
			}
			catch (IOException e) {
				judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
				
			}
		}
	}
	
	public void  clearTemp(){
		try {
			Runtime.getRuntime().exec(clear_command+" "+problem.getProblem_id()+" "+user.getUser_id());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
