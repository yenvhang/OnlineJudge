package top.yenvhang.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.judge.core.JudgeBin2;
import top.yenvhang.mapper.CodeMapper;
import top.yenvhang.mapper.JudgeResultMapper;
import top.yenvhang.mapper.RunInfoMapper;
import top.yenvhang.mapper.SubmissionMapper;
import top.yenvhang.model.JudgeResult;
import top.yenvhang.model.Problem;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;

@Service
public class SubmissionService {
	@Autowired
	private SubmissionMapper submissionMapper;
	@Autowired
	JudgeResultMapper judgeResultMapper;
	@Autowired
	RunInfoMapper runInfoMapper;
	@Autowired
	CodeMapper codeMapper;
	@Autowired
	AccountService accountService;
	@Autowired
	ProblemService problemService;
	@Autowired
	JudgeBin2 judgeBin2;
	@Autowired
//	private MessageSender messageSender;
	/**
	 * 创建Submission
	 * @param user
	 * @param problem_id
	 * @param code 
	 * @return
	 */ 
	
	public Submission createSubmission(User user, long problem_id, String str_code) {
		Problem problem =problemService.getProblemById(problem_id);
		Submission submission =new Submission(problem, user, new Date(),str_code);  
		submissionMapper.insertSubmission(submission);
		long submissionId=submission.getSubmission_id();
		createSubmissionTask(submissionId);
//		RunInfo runInfo =new RunInfo();
//		JudgeResult judgeResult=new JudgeResult();
//		judgeBin2.createNewJudgeTask(submission,code.getCode(),new JudgeListener(judgeResult, runInfo));
//		judgeResult =judgeResultMapper.getJudgeResultUsingName(judgeResult.getJudgeResultName());
//		submission.setJudgeResult(judgeResult);
		
		
//

//		codeMapper.insertCode(code);
//		accountService.updateUserMessgae(user.getUser_id(),user.getSubmitted(),user.getSolved(),user.getScore());
//		problemService.UpdateProblemMessage(problem.getProblem_id(), problem.getSubmitted(),problem.getSolved());
		
		return submission;
	}
	
	private void createSubmissionTask(long submissionId) {
		
			Map<String, Object> mapMessage = new HashMap<String, Object>();
			mapMessage.put("event", "SubmissionCreated");
			mapMessage.put("submissionId", submissionId);
			
//			messageSender.sendMessage(mapMessage);
		}
		
			
		
	

	private String queryCode(int submissionId){
		return submissionMapper.selectCodeBySubmissionId(submissionId);
	}
	/**
	 * 设置提交结果
	 * @return
	 */
	public void UpdateSubmission(Submission submission){
		
		
	}
	public List<Submission> getSubmissions(){
		return submissionMapper.getSubmissions(); 
	}
	public List<Submission> getSubmissionsUsingFilter(int startIndex, int limit,String judgeResultName) {
		JudgeResult judgeResult =judgeResultMapper.getJudgeResultUsingName(judgeResultName);
		return submissionMapper.getSubmissionsUsingFilter(startIndex,limit,judgeResult);
		
	}
	public long getNumsOfSubmissions() {
		return submissionMapper.getNumsOfSubmissions();
		
	}
	public List<Submission> getSubmissionUsingUserAndProblem_id(long user_id,long problem_id){
		
		return submissionMapper.getSubmissionUsingUserAndProblem_id(user_id, problem_id);
	}
	
	public  List<Submission> getSubmissionUsingSubmissionId(long submissionId){
		return submissionMapper.getSubmissionUsingSubmission_id(submissionId);
	}
	public long getLastSubmissionId(){
		return submissionMapper.getMaxSubmissionId();
	}
	


}
