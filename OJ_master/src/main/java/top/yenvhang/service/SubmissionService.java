package top.yenvhang.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.JudgeBin;
import top.yenvhang.mapper.CodeMapper;
import top.yenvhang.mapper.JudgeResultMapper;
import top.yenvhang.mapper.RunInfoMapper;
import top.yenvhang.mapper.SubmissionMapper;
import top.yenvhang.mapper.UserMapper;
import top.yenvhang.model.Code;
import top.yenvhang.model.JudgeResult;
import top.yenvhang.model.Problem;
import top.yenvhang.model.RunInfo;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;
import util.StringUtil;

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
	/**
	 * 创建Submission
	 * @param user
	 * @param problem_id
	 * @param code 
	 * @return
	 */ 
	
	public Submission createSubmission(User user, long problem_id, String str_code) {
		Problem problem =problemService.getProblemById(problem_id);
		Submission submission =new Submission(problem, user, new Date());  
		Code code =new Code(); 
		code.setCode(str_code);
		RunInfo runInfo =new RunInfo();
		JudgeBin judgeBin =new JudgeBin(user, problem, submission,code,runInfo);
		judgeBin.process();
		JudgeResult judgeResult =judgeResultMapper.getJudgeResultUsingName(submission.getJudgeResult().getJudgeResultName());
		submission.setJudgeResult(judgeResult);
		submissionMapper.insertSubmission(submission);
		long submissionId=getLastSubmissionId();
		if(!StringUtil.isEmpty(runInfo.getRunInfo())){
			runInfo.setSubmissionId(submissionId);
			runInfoMapper.insertRunInfo(runInfo);
		}
		code.setSubmissionId(submissionId);
		codeMapper.insertCode(code);
		accountService.updateUserMessgae(user.getUser_id(),user.getSubmitted(),user.getSolved(),user.getScore());
		problemService.UpdateProblemMessage(problem.getProblem_id(), problem.getSubmitted(),problem.getSolved());
		
		return submission;
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
