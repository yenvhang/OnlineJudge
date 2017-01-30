package top.yenvhang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.mapper.RunInfoMapper;

@Service
public class RunInfoService {
	@Autowired
	RunInfoMapper runInfoMapper;
	
	public String getRunInfoBySubmissionId(long submissionId){
		return runInfoMapper.queryRunInfoBySbumissionId(submissionId);
	}
}
