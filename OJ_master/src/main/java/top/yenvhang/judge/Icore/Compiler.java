package top.yenvhang.judge.Icore;


/**
* Project Name:OJ_master
* File Name:Compiler.java
* Package Name:core
* Date:2017年2月9日下午3:43:49
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

import java.util.Map;

import top.yenvhang.model.Submission;

/**
* ClassName:Compiler <br/>
* Function: TODO 编译代码. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月9日 下午3:43:49 <br/>
* @author 叶宇航
* @version
* @see
*/
public interface Compiler {
	//获取编译结果
	public Map<String,Object> getCompileResult(Submission submission,String workDirectory,String fileName);
	//获取编译命令
	public String getCompileCommandLine();
	//获取编译日志
	public String getCompileLog();
	//编译
	public boolean compile(Submission submission);
		
		
	
	
}

