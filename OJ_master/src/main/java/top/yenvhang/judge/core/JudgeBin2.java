package top.yenvhang.judge.core;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.yenvhang.judge.Icore.Preprocessor;
import top.yenvhang.judge.Icore.Runner;
import top.yenvhang.mapper.CheckPointMapper;
import top.yenvhang.model.CheckPoint;
import top.yenvhang.model.JudgeResult;
import top.yenvhang.model.Submission;
import top.yenvhang.judge.Icore.Comparator;
import top.yenvhang.judge.Icore.Compiler;
import top.yenvhang.judge.Icore.JudgeCallBack;

/**
* ClassName:JudgeBin2 <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月11日 上午11:29:02 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class JudgeBin2 {
	
	
	@Autowired
	private Preprocessor defaultPreprocessor;
	@Autowired
	private Compiler shellCompiler;
	@Autowired
	private Runner shellRunner;
	@Autowired
	private Comparator shellComparator;
	@Autowired
	private CheckPointMapper checkPointMapper;



	private static String workDirectory="/var/OnlineJudge/code"; 
	private static String fileName="Main";
	
	public void createNewJudgeTask(Submission submission,String code,JudgeCallBack judgeCallBack){
		Map<String,Object> result=new HashMap<String, Object>();
		judgeCallBack.preProcessBegin();
		boolean IsOK =defaultPreprocessor.preProcess(submission,code,workDirectory,fileName);
		if(IsOK){
			judgeCallBack.preProcessFinsh();
			result=shellCompiler.getCompileResult(submission, workDirectory,submission.getSubmission_id()+"");
			if(result.get("error")==null){
				judgeCallBack.compileFinsh();
				List<CheckPoint> checkPoints=checkPointMapper.fetchCheckPoints(submission.getProblem().getProblem_id());
				for(CheckPoint checkPoint:checkPoints){
					result=shellRunner.getRunResult(submission,checkPoint);
					if(result.get("isSuccessful")!=null&&(Boolean)(result.get("isSuccessful"))){
						//正确运行
						result=shellComparator.getCompareResult(submission,checkPoint);
						//对比正确
						
						if(result.get("isSuccessful")!=null&&(Boolean)(result.get("isSuccessful"))){
							judgeCallBack.runPartFinsh(submission, checkPoint,true);
						}
						//答案错误
						else{
							judgeCallBack.runPartFinsh(submission, checkPoint, false);
							
							break;
						}
					}
					else if(result.get("isOverTime")!=null&&(Boolean)(result.get("isOverTime"))){
						//运行超时
						judgeCallBack.runTimeOut();
						break;
					}
					else{
						//运行错误
						judgeCallBack.runError(result.get("error")+"");
						break;
					}
				}
			}	
			else{
				judgeCallBack.compileError(result.get("error")+"");
			}
		}
		else{
			judgeCallBack.preProcessError();
		}
	
	
	
	}
}

