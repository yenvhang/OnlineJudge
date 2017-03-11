package top.yenvhang.judge.core;


/**
* Project Name:OJ_master
* File Name:abstractCompiler.java
* Package Name:core
* Date:2017年2月11日上午9:57:57
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import top.yenvhang.judge.Icore.Compiler;
import top.yenvhang.judge.Icore.JudgeCallBack;
import top.yenvhang.judge.util.IOUtils;
import top.yenvhang.model.Submission;

/**
* ClassName:abstractCompiler <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月11日 上午9:57:57 <br/>
* @author 叶宇航
* @version
* @see
*/ 
public abstract class AbstractCompiler implements Compiler {
	
	Map<String,Object> result;
	InputStream error_ins;
	public Map<String,Object> getCompileResult(Submission submission,String workDirectory,String fileName){
		 result=new HashMap<String, Object>();
	
		if(compile(submission)){
			result.put("isSuccessful", true);
		
		}
		else{
		
		}
		return result;
	}
	public String getCompileCommandLine(){
		return compileCommandLine;
	}
	public String getCompileLog(){
		return IOUtils.getString(error_ins);
	}
	
	private static String compileCommandLine="/var/OnlineJudge/sh/ojJavac.sh";
}

