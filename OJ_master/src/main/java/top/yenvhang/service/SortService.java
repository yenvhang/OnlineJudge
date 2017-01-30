package top.yenvhang.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.mapper.SubmissionMapper;
import top.yenvhang.mapper.UserMapper;
import top.yenvhang.model.Sort;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;

@Service
public class SortService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	SubmissionMapper submissionMapper;
	/**
	 * 获得排序后的用户列表
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<User> getUsersUsingFilters(int startIndex, int pageSize) {
		return userMapper.getUsers(startIndex, pageSize);
	}
	/**
	 * 获取用户数量
	 * @return
	 */
	public long getNumsofUsers(){
		return userMapper.getNumsofUsers();
	}
	
	/**
	 * 获取AC指定题目的提交数
	 */
	public long getNumsOfACsubmissionUsingProblemId(long problmeId){
		return submissionMapper.getNumsOfACsubmissionUsingProblemId(problmeId);
	}
	/**
	 * 获取指定用户的排名
	 * @param userId
	 * @return
	 */
	public long getRankUsingUserId(long userId){ 
		return userMapper.getRankUsingUserId(userId);
	}
	/**
	 * 获取完成指定题目　的排序用户列表
	 * @param startIndex
	 * @param pageSize
	 * @param userId
	 * @param problemId
	 * @return
	 */
	public List<Submission> getUsersACUsingProblemId(int startIndex, int pageSize,long problemId) {
		return submissionMapper.getUsersACUsingProblemId(startIndex, pageSize, problemId);
	}
	/**
	 * 获取完成指定题目的指定用户的排名
	 * @param problemId
	 * @param userId
	 * @return
	 */
	public long getRankUsingProblemAndUserId(long problemId,long userId){
		return submissionMapper.getRankUsingProblemAndUserId(problemId, userId);
	}
	public List<Sort> getWeekSort(Date start,Date end){
		return submissionMapper.getWeekSort(start, end);
	}
	
	
}
