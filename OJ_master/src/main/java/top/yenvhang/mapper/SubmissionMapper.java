package top.yenvhang.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yenvhang.model.JudgeResult;
import top.yenvhang.model.Sort;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;

public interface SubmissionMapper {

	public void insertSubmission(Submission submission); 
	public List<Submission> getSubmissionsUsingFilter(@Param("startIndex")int startIndex,@Param("limit")int limit,@Param("judgeResult")JudgeResult judgeResult);
	public List<Submission> getSubmissions();
	public List<Submission> getSubmissionUsingSubmission_id(@Param("submission_id")long submission_id);
	public Submission getSubmissionUsingUser_id(@Param("user_id")long user_id);
	public List<Submission> getSubmissionUsingUserAndProblem_id(@Param("user_id")long user_id,@Param("problem_id") long problem_id);
	public List<Submission> getSubmissionUsingProblem_id(@Param("problem_id") long problem_id);
	public List<Submission> getAcceptedSubmissionUsingUser_id(@Param("user_id")long user_id);
	public List<Submission> getAcceptedSubmissions();
	public void updateSubmission(Submission submission);
	public long getNumsOfSubmissions();
	public String selectCodeBySubmissionId(int submission_id);
	public long getMaxSubmissionId();
	/**
	 * 获取完成指定题目　排序后的用户列表
	 * @param start
	 * @param limit
	 * @param userId
	 * @param problemId
	 * @return
	 */
	public List<Submission>getUsersACUsingProblemId(@Param("start") int start,
			@Param("limit") int limit,
			@Param("problem_id") long problemId);
		
	
	/**
	 * 获取指定题目　对应用户的排名
	 * @param user_id 
	 * @param problem_id
	 * @return 返回　指定题目　对应用户的排名
	 */
	public long getRankUsingProblemAndUserId(@Param("problem_id") long problem_id,@Param("user_id")long user_id);
	/**
	 * 获取AC指定题目的提交数
	 */
	public long getNumsOfACsubmissionUsingProblemId(@Param("problem_id")long problmeId);
	/**
	 * 获取每周排名
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Sort> getWeekSort(@Param("start")Date start,@Param("end")Date end);
	/**
	 * 获取已经通过的题目
	 * @param userId
	 * @return
	 */
	public List<Long>getSolvedProblemIds(@Param("user_id")long userId);
	/**
	 * 获取正在攻克的题目
	 */
	public List<Long>getBeingSolvedProbelmIds(@Param("user_id")long userId);
}

