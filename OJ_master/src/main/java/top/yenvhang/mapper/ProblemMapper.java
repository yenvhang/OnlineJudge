package top.yenvhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yenvhang.model.Problem;


public interface ProblemMapper {
	public List<Problem> getProblemsUsingFilter(@Param("start") long start,@Param("limit")int limit);
	public int getFirstIndexOfProblems();
	public int getNumsofProblems();
	public void insertProblem(Problem problem);
	public Problem getProblemById(@Param("problem_id")long problem_id);
	public int updateProblemMessage(@Param("problem_id")long problemId,@Param("submitted")int submitted,@Param("solved")int solved);
}
