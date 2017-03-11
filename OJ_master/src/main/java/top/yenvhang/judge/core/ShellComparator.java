package top.yenvhang.judge.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import top.yenvhang.judge.Icore.Comparator;
import top.yenvhang.judge.util.IOUtils;
import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.Submission;

/**
* Project Name:OJ_master
* File Name:ShellComparator.java
* Package Name:core
* Date:2017年2月11日上午10:13:28
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

/**
* ClassName:ShellComparator <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月11日 上午10:13:28 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class ShellComparator implements Comparator {
	Map<String,Object> result =new HashMap<String, Object>();
	public String getCompareCommanLine() {
		return comparateCommand;
	}
	public boolean compare(Submission submission, CheckPoint checkPoint) {
			try {
				Process process =Runtime.getRuntime().exec(getCompareCommanLine()+" "+submission.getSubmission_id()+" "+checkPoint.getCheckPointId());
				process.waitFor();
				String msg=getCompileLog(process.getInputStream());
				if(StringUtils.isEmpty(msg)){
					result.put("isSuccessful", true);
					return true;
				}
				else{
					result.put("error",msg);
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			return true;
	}
	public Map<String, Object> getCompareResult(Submission submission,CheckPoint checkPoint) {
		compare(submission, checkPoint);
		return result;
	}

	public String getCompileLog(InputStream ins) {
		return IOUtils.getString(ins);
	}
	private static String comparateCommand="/var/OnlineJudge/sh/ojDiff.sh";



}

