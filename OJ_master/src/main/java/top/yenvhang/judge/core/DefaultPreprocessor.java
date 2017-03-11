package top.yenvhang.judge.core;



/**
* Project Name:OJ_master
* File Name:Preprocessor.java
* Package Name:core
* Date:2017年2月9日下午3:54:00
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.yenvhang.judge.Icore.JudgeCallBack;
import top.yenvhang.judge.Icore.Preprocessor;
import top.yenvhang.mapper.CheckPointMapper;
import top.yenvhang.mapper.CodeMapper;
import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.Submission;


/**
* ClassName:Preprocessor <br/>
* Function: TODO 预处理
* １将提交的代码写到指定文件中
* ２将题目对应的测试点写到指定文件中 <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月9日 下午3:54:00 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class DefaultPreprocessor implements Preprocessor{
	private static String checkPointDir="/var/OnlineJudge/checkPoints";
	@Autowired
	private CheckPointMapper checkPointMapper;
	
	public boolean preProcess(Submission submission,String code,String workDirectory,String fileName){
		//获取提交的代码
		//获取题目对应的测试点
	
		List<CheckPoint> checkPoints=fetchCheckPoints(submission.getProblem().getProblem_id());
			try {
				writeCode2File(code, generateCodeFile(workDirectory, fileName,submission.getSubmission_id()));
				writeCheckPoints2File(checkPoints);
			
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return true;
	}
	//保存指定代码到文件中
	public void writeCode2File(String code,OutputStream codeStream) throws IOException{
		IOUtils.write(code, codeStream);
		IOUtils.closeQuietly(codeStream);
	} 
	//获取测试点
	public List<CheckPoint> fetchCheckPoints(long problemId){
		return checkPointMapper.fetchCheckPoints(problemId);
	}
	
//	public String fetchCode(long submissionId){
//		return codeMapper.queryCodeBySbumissionId(submissionId);
//	}
	/**
	 * 
	* writeCheckPoints2File:(将测试数据写入到指定文件中). <br/>
	* TODO(这里描述这个方法适用条件 – 可选).<br/>

	*
	* @author 叶宇航
	* @param checkPoints
	* @param input　　测试点　输入数据流
	* @param output　测试点　输出数据流
	 * @throws FileNotFoundException 
	 */
	public void writeCheckPoints2File(List<CheckPoint> checkPoints) throws FileNotFoundException{
		for(CheckPoint checkPoint:checkPoints){
				try {
					FileOutputStream incheck =generateINCheckPointsFile(checkPoint);
					IOUtils.write(checkPoint.getInput(), incheck);
					IOUtils.closeQuietly(incheck);
					FileOutputStream outcheck=generateOUTCheckPointsFile(checkPoint);
					IOUtils.write(checkPoint.getOutput(),outcheck);
					IOUtils.closeQuietly(outcheck);
				
				} catch (IOException e) {
						e.printStackTrace();
						break;
					
				}
				
		}
	}
	public FileOutputStream generateCodeFile(String workDirectory,String fileName,long submissionId) throws FileNotFoundException {
		String codeFilePath=null;
		codeFilePath =String.format("%s/%s/%s.%s", new Object[]{workDirectory,submissionId,fileName,getCodeFileSuffix()});
		File file =new File(workDirectory+"/"+submissionId);
		if(!file.exists()){
			file.mkdirs();
		}
		return new FileOutputStream(new File(codeFilePath));
	}
	public String getCodeFileSuffix() {
			return "java";
	}
	public FileOutputStream generateINCheckPointsFile(CheckPoint checkPoint) throws FileNotFoundException{
		String checkPointFilePath = null; 
		checkPointFilePath=String.format("%s/input%s.txt", new Object[]{checkPointDir,checkPoint.getCheckPointId()});
		File file =new File(checkPointDir);
		if(!file.exists()){
			file.mkdirs();
		}
		return new FileOutputStream(checkPointFilePath);
	}
	public FileOutputStream generateOUTCheckPointsFile(CheckPoint checkPoint) throws FileNotFoundException{
		String checkPointFilePath = null;
		checkPointFilePath=String.format("%s/output%s.txt", new Object[]{checkPointDir,checkPoint.getCheckPointId()});
		File file =new File(checkPointDir);
			if(!file.exists()){
			file.mkdirs();
		}
		return new FileOutputStream(checkPointFilePath);
	}
	public String fetchCode(long submissionId) {
			return null;
		
	}

}

