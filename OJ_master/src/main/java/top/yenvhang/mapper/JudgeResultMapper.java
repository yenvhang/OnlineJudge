package top.yenvhang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import top.yenvhang.model.JudgeResult;


public interface JudgeResultMapper {
	@Select("select * from oj_judgeResult where result_id=#{result_id}")
	public JudgeResult getJudgeResult(int result_id);
	@Select("select * from oj_judgeResult where judgeResultName=#{judgeResultName}")
	public JudgeResult getJudgeResultUsingName(String judgeResultName);
	@Insert("insert into oj_judgeResult values(#{result_id},#{judgeResultName})")
	public void InsertJugeResult(JudgeResult judgeResult);
	
}
