package top.yenvhang.judge.core;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.JudgeResult;
import top.yenvhang.model.Submission;

/**
* ClassName:Runner <br/>
* Function: TODO 运行编译后的字节码. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月9日 下午3:44:32 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class ShellRunner extends AbstractRunner {
	private JudgeResult judgeResult;
	public boolean run(Submission submission,CheckPoint checkPoint) {
		
		try {
			preRun(submission, checkPoint);
			long startTime =System.currentTimeMillis();
			final Process process =Runtime.getRuntime().exec(getRunCommandLine()+" "+submission.getSubmission_id()+" "+checkPoint.getCheckPointId());
			ScheduledExecutorService service=
					Executors.newSingleThreadScheduledExecutor();
			service.schedule(new Runnable() {
				public void run() {
					process.destroy();
					System.out.println("中断");
					
				}
			},getRunTimeLimit(submission), TimeUnit.SECONDS);
			process.waitFor();
			long endTime =System.currentTimeMillis();
			service.shutdownNow();
			if(endTime-startTime>=submission.getProblem().getTime_limit()){
				logger.error("SubmissionId:"+submission.getSubmission_id()+
						"\nCheckPoint:"+checkPoint +" overTime");
				result.put("isOverTime", true);
				
				return true;
			}
			String error=getRunLog(submission,checkPoint);
			if(!StringUtils.isEmpty(error)){
				result.put("error",error);
				logger.error("SubmissionId:"+submission.getSubmission_id()+
						"\nCheckPoint:"+checkPoint +"\n error:"+error);
				
				return false;
			}
			result.put("isSuccessful", true);
			return true;
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			submission.setJudgeResult(judgeResult);
		}
		}
	

	@SuppressWarnings("static-access")
	private void preRun(Submission submission,CheckPoint checkPoint) {
		String outputFilePath=String.format("%soutput%s.txt",
				new Object[]{submission.getSubmission_id(),checkPoint.getCheckPointId()});
		File outputFile=new File(outputDir);
		if(!outputFile.exists())outputFile.mkdirs();
		String errorFilePath=String.format("%serr_run_log%s.txt", 
				new Object[]{submission.getSubmission_id(),checkPoint.getCheckPointId()});
		File errorFile=new File(errDir);
		if(!errorFile.exists())errorFile.mkdirs();
		try {
			if(!new File(outputDir,outputFilePath).exists()){
				new File(outputDir,outputFilePath).createNewFile();
			}
			if(!new File(errDir,errorFilePath).exists()){
				new File(errDir,errorFilePath).createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		
	}
		
		
	
		
		
	}

	private static Logger logger = Logger.getLogger(ShellRunner.class);
	private String outputDir="/var/OnlineJudge/temp";
	private String errDir="/var/OnlineJudge/error";
	

}

