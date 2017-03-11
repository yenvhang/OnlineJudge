
/**
* Project Name:OJ_master
* File Name:JudgeListener.java
* Package Name:top.yenvhang.judge.core
* Date:2017年2月11日下午9:56:15
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.judge.core;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.yenvhang.judge.Icore.JudgeCallBack;
import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.JudgeResult;
import top.yenvhang.model.Problem;
import top.yenvhang.model.RunInfo;
import top.yenvhang.model.Submission;

/**
* ClassName:JudgeListener <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月11日 下午9:56:15 <br/>
* @author 叶宇航
* @version
* @see
*/
public class JudgeListener implements JudgeCallBack{
	private RunInfo runInfo;
	private JudgeResult judgeResult;
	public JudgeListener(JudgeResult judgeResult,RunInfo runInfo){
		this.judgeResult=judgeResult;
		this.runInfo=runInfo;
	}
	private static Logger logger = Logger.getLogger(JudgeListener.class); 
	public void preProcessBegin() {
		logger.info("preProcessBegin");
	}

	public void preProcessFinsh() {
		logger.info("preProcessFinsh");
		
	}

	public void preProcessError() {
		logger.info("preProcessError");
	}

	public void compileBegin() {
		logger.info("compileBegin");
		judgeResult.setJudgeResultName(JudgeResult.COMPILING);
	}

	public void compileFinsh() {
		logger.info("compileFinsh");
		judgeResult.setJudgeResultName(JudgeResult.COMPILE_SUCCEED);
	}


	public void compileError(String str) {
		logger.info("systemError:"+str);
		judgeResult.setJudgeResultName(JudgeResult.COMPILE_FAILED);
		runInfo.setRunInfo(str);
	}

	public void compileError() {
		logger.info("compileError");
		judgeResult.setJudgeResultName(JudgeResult.COMPILE_FAILED);
	}


	public void runBegin() {
		logger.info("runBegin");
	}

	public void runFinsh() {
		logger.info("runFinsh");
		judgeResult.setJudgeResultName(JudgeResult.AC);
		
	}
	public void runError(String str) {
		logger.info("runError");
		judgeResult.setJudgeResultName(JudgeResult.RUNTIM_EERROR);
		runInfo.setRunInfo(str);
	}

	public void runTimeOut() {
		logger.info("runTimeOut");
		judgeResult.setJudgeResultName(JudgeResult.RUNTIME_LIMIT);
	}

	public void runMemoryOut() {
		logger.info("runMemoryOut");
		judgeResult.setJudgeResultName(JudgeResult.MEMORY_LIMIT);
	}
	public void systemError(Exception e){
		logger.info("systemError"+e.getMessage());
		judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
	}
	public void systemError() {
		judgeResult.setJudgeResultName(JudgeResult.SYSTEM_ERROR);
	}

	public void runPartFinsh(Submission submission,CheckPoint checkPoint,boolean isCorrect) {
		if(isCorrect){
			logger.info("runPartFinsh correct");
			submission.setJudgeScore(submission.getJudgeScore()+checkPoint.getScore());
		}
		else{
			logger.info("runPartFinsh incorrect");
			judgeResult.setJudgeResultName(JudgeResult.WRONG_ANSWER);
			
		}
	}

	public void runFinsh(boolean isCorrect) {
			if(isCorrect){
				judgeResult.setJudgeResultName(JudgeResult.AC);
				
			}
			else{
				judgeResult.setJudgeResultName(JudgeResult.WRONG_ANSWER);
			}
		
	}

	

}

