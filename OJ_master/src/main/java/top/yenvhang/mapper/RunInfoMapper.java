package top.yenvhang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import top.yenvhang.model.RunInfo;

public interface RunInfoMapper {
		@Select({"select runInfo from oj_runInfo where submissionId=#{submissionId}"})
		public String queryRunInfoBySbumissionId(long submissionId);
		@Insert({"insert into oj_runInfo values(#{submissionId},#{runInfo})"})
		public int insertRunInfo(RunInfo runInfo);
}
