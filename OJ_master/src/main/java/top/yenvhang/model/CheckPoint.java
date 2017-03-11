package top.yenvhang.model;

/**
* Project Name:OJ_master
* File Name:checkPoint.java
* Package Name:core
* Date:2017年2月9日下午4:11:07
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

/**
* ClassName:checkPoint <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月9日 下午4:11:07 <br/>
* @author 叶宇航
* @version
* @see
*/
public class CheckPoint {
	/**
	 * 题目Id
	 */
	private long problemId;
	/**
	 * 测试点Id
	 */
	private long checkPointId;
	
	/**
	 * 标准输入
	 */
	private String input;
	/**
	 * 标准输出
	 */
	private String output;
	/**
	 * 分值
	 */
	private int score;
	/**
	 * 是否匹配测试点
	 */
	private boolean isExactlyMatch;
	public long getProblemId() {
	
		return problemId;
	}
	public void setProblemId(long problemId) {
	
		this.problemId = problemId;
	}
	public long getCheckPointId() {
	
		return checkPointId;
	}
	public void setCheckPointId(long checkPointId) {
	
		this.checkPointId = checkPointId;
	}
	public String getInput() {
	
		return input;
	}
	public void setInput(String input) {
	
		this.input = input;
	}
	public String getOutput() {
	
		return output;
	}
	public void setOutput(String output) {
	
		this.output = output;
	}
	public int getScore() {
	
		return score;
	}
	public void setScore(int score) {
	
		this.score = score;
	}
	public boolean isExactlyMatch() {
	
		return isExactlyMatch;
	}
	public void setExactlyMatch(boolean isExactlyMatch) {
	
		this.isExactlyMatch = isExactlyMatch;
	}
	
	@Override
	public String toString() {
		return "checkPointId:"+checkPointId+"input:"+input+"output:"+output;
		
	}
	
}

