package top.yenvhang.model;

import java.io.Serializable;
import java.util.Date;

public class Submission implements Serializable{
	/**
	 * 
	 */
	private long submission_id; 
	private Problem problem;
	private User user;
	private Date submitTime;
	private Date executeTime;
	private long useTime;
	private int usememory;
	private JudgeResult judgeResult;
	private int judgeScore;

	public Submission(){}
	//提交代码　的构造函数
	public Submission(long submissionId, Problem problem, User user, Date submitTime) {
		this.submission_id = submissionId;
		this.problem = problem;
		this.user = user;
		this.submitTime = submitTime;
		
	}
	public Submission(Problem problem, User user, Date submitTime) {
		this.problem=problem;
		this.user = user;
		this.submitTime = submitTime;
	
	}

	
	public long getSubmission_id() {
		return submission_id;
	}
	public void setSubmission_id(long submission_id) {
		this.submission_id = submission_id;
	}
	public Problem getProblem() {
		return problem;
	}
	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}
	public long getUseTime() {
		return useTime;
	}
	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}
	public int getUsememory() {
		return usememory;
	} 
	public void setUsememory(int usememory) {
		this.usememory = usememory;
	}
	public JudgeResult getJudgeResult() {
		return judgeResult;
	}
	public void setJudgeResult(JudgeResult judgeResult) {
		this.judgeResult = judgeResult;
	}
	public int getJudgeScore() {
		return judgeScore;
	}
	public void setJudgeScore(int judgeScore) {
		this.judgeScore = judgeScore;
	}
	
	
}
