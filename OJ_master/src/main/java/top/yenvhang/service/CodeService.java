package top.yenvhang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.mapper.CodeMapper;

@Service
public class CodeService {
	@Autowired
	CodeMapper codeMapper;
	public String getCodeBySubmissionId(long submissionId){
		return codeMapper.queryCodeBySbumissionId(submissionId);
	}
}
