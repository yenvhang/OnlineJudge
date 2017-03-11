package top.yenvhang.judge.core;


import java.io.File;

/**
* Project Name:OJ_master
* File Name:AbstractRunner.java
* Package Name:core
* Date:2017年2月11日上午10:01:17
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import top.yenvhang.judge.Icore.Runner;
import top.yenvhang.judge.util.IOUtils;
import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.Submission;

/**
* ClassName:AbstractRunner <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月11日 上午10:01:17 <br/>
* @author 叶宇航
* @version
* @see
*/
public abstract class AbstractRunner implements Runner {
	Map<String,Object> result =new HashMap<String, Object>();
	public Map<String, Object> getRunResult(Submission submission,CheckPoint checkPoint) {
		run(submission, checkPoint);
		return result;
	
}  
	public String getRunCommandLine() {
			return runCommandLine;
	}
	public String getRunLog(Submission submission,CheckPoint checkPoint) {
		try {
			FileInputStream fil=new FileInputStream(new File("/var/OnlineJudge/error/"+
			submission.getSubmission_id()+"err_run_log"+checkPoint.getCheckPointId()+".txt"));
			return IOUtils.getString(fil);
		} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
		}
	}
	public long getRunTimeLimit(Submission submission) {
			return submission.getProblem().getTime_limit();
		
	}
	public long getMemoryLimit(Submission submission) {
			return submission.getProblem().getMemory_limit();
		
	}
	
	@Value("")
	private static String runCommandLine="/var/OnlineJudge/sh/ojJava.sh";
}

