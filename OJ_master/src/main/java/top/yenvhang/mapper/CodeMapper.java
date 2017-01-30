package top.yenvhang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import top.yenvhang.model.Code;

public interface CodeMapper {
	@Select({"select code from oj_code where submissionId=#{submissionId}"})
	public String queryCodeBySbumissionId(long submissionId);
	
	@Insert({"insert into oj_code values(#{submissionId},#{code})"})
	public int insertCode(Code code);

}
