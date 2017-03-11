package top.yenvhang.judge.Icore;


import java.io.InputStream;

/**
* Project Name:OJ_master
* File Name:Comparator.java
* Package Name:core
* Date:2017年2月9日下午3:43:27
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/


import java.util.Map;

import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.Submission;

/**
* ClassName:Comparator <br/>
* Function: TODO 对比运行结果. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月9日 下午3:43:27 <br/>
* @author 叶宇航
* @version
* @see
*/
public interface Comparator {
	public String getCompareCommanLine();
	public boolean compare(Submission submission,CheckPoint checkPoint);
	public Map<String,Object>getCompareResult(Submission submission,CheckPoint checkPoint);
	public String getCompileLog(InputStream ins);
}

