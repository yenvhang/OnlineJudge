package top.yenvhang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface CodeMapper {
	@Select({"select code from oj_code where submissionId=#{submissionId}"})
	public String queryCodeBySbumissionId(long submissionId);
	
}
