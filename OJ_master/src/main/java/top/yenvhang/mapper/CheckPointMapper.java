


package top.yenvhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import top.yenvhang.model.CheckPoint;
public interface CheckPointMapper {
	String TABLE_NAME="oj_check_points";
	String SELECT_FIELDS="checkPointId,problemId,input,output,score";
	@Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where problemId=#{problemId} and isExactlyMatch=true"})
	public List<CheckPoint> fetchCheckPoints(long problemId);
}

