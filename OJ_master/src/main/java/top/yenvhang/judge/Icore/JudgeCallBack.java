
/**
* Project Name:OJ_master
* File Name:CompileCallBack.java
* Package Name:top.yenvhang.judge.Icore
* Date:2017年2月11日下午9:49:45
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.judge.Icore;

import java.io.InputStream;

import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.Submission;

/**
* ClassName:CompileCallBack <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月11日 下午9:49:45 <br/>
* @author 叶宇航
* @version
* @see
*/
public interface JudgeCallBack {
	public void preProcessBegin();
	public void preProcessFinsh();
	public void preProcessError();
	
	public void compileBegin();
	public void compileFinsh();
	public void compileError(String str);
	public void compileError();
	public void runBegin();
	public void runFinsh(boolean isCorrect);
	public void runPartFinsh(Submission submission,CheckPoint checkPoint,boolean isCorrect);
	public void runError(String str);
	
	public void runTimeOut();
	public void runMemoryOut();
	public void systemError();
	public void systemError(Exception e);
	
}

