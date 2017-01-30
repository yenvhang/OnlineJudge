package top.yenvhang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import top.yenvhang.mapper.ProblemMapper;
import top.yenvhang.mapper.SubmissionMapper;
import top.yenvhang.model.Problem;
@Service
public class ProblemService {
	@Autowired
	private ProblemMapper problemMapper; 
	@Autowired
	private SubmissionMapper submissionMapper;
	public long getFirstIndexOfProblems() {
		return problemMapper.getFirstIndexOfProblems();
	}
	
	/**
	 * 获取试题列表.
	 * @param offset - 试题唯一标识符的起始序号
	 * @param keyword - 关键字
	 * @param problemCategorySlug - 试题分类的别名
	 * @param problemTagSlug - 试题标签的别名
	 * @param limit - 每次加载试题的数量
	 * @return 试题列表(List<Problem>对象)
	 */
	public List<Problem> getProblemsUsingFilters(long start,int limit) {
		return problemMapper.getProblemsUsingFilter(start, limit);
	}
	/**
	 * 通过试题的唯一标识符获取试题的详细信息.
	 * @param problemId - 试题的唯一标识符
	 * @return 试题的详细信息
//	 */
//	public Problem getProblem(long problemId) {
//		return problemDAO.getProblem(problemId);
//	}
	/**
	 * 获取已经通过的题目
	 * @param userId
	 * @return
	 */
	public List<Long> getSolvedProblemId(long userId){
		return submissionMapper.getSolvedProblemIds(userId);
	}
	/**
	 * 获取正在攻克的题目
	 * @param userId
	 * @return
	 */
	public List<Long> getBeingSolvedProbelmId(long userId){
		return submissionMapper.getBeingSolvedProbelmIds(userId);
	}

	public long getLastIndexOfProblems(boolean b, long startIndex, Object numberOfProblemsPerPage) {
		return 0;
	}

	public int getNumsofProblems() {
		return problemMapper.getNumsofProblems();
	}
	public void insertProblem(Problem problem){
		problemMapper.insertProblem(problem);
	}

	public Problem getProblemById(long problem_id) {
		return problemMapper.getProblemById(problem_id);
	}
	
	public int UpdateProblemMessage(long problemId,int submitted,int solved){
		return problemMapper.updateProblemMessage(problemId, submitted, solved);
	}
	
}
