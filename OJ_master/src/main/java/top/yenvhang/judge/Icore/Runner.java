package top.yenvhang.judge.Icore;


/**
* Project Name:OJ_master
* File Name:Runner.java
* Package Name:core
* Date:2017年2月9日下午3:44:32
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/


import java.util.Map;

import top.yenvhang.model.CheckPoint;
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
public interface Runner {
	public String getRunCommandLine();
	public long getRunTimeLimit(Submission submission);
	public long getMemoryLimit(Submission submission);
	public boolean run(Submission submission,CheckPoint checkPoint);
	public String getRunLog(Submission submission,CheckPoint checkPoint);
	public Map<String,Object> getRunResult(Submission submission,CheckPoint checkPoint);
}

