package top.yenvhang.judge.core;


/**
* Project Name:OJ_master
* File Name:ShellCompiler.java
* Package Name:core
* Date:2017年2月11日上午10:04:12
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/


import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import top.yenvhang.controller.AccountsController;
import top.yenvhang.judge.Icore.JudgeCallBack;
import top.yenvhang.model.Submission;

/**
* ClassName:ShellCompiler <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月11日 上午10:04:12 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class ShellCompiler extends AbstractCompiler{
	
	public boolean compile(Submission submission) {
		try {
			
			Process process =Runtime.getRuntime().exec(getCompileCommandLine()+" "+submission.getSubmission_id());
			process.waitFor();
			error_ins=process.getErrorStream();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String error=getCompileLog();
		if(!StringUtils.isEmpty(error)){
			logger.error("compile error :"+error);
			result.put("error", error);
			return false;
		}
		return true;
	}
	
	 private static Logger logger = Logger.getLogger(ShellCompiler.class);  
}

