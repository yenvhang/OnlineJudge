package top.yenvhang.judge.Icore;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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

public interface Preprocessor {
	public boolean preProcess(Submission submission,String code,String workDirectory,String fileName);
	public void writeCode2File(String code,OutputStream codeStream) throws IOException ;
	public String fetchCode(long submissionId);
	public void writeCheckPoints2File(List<CheckPoint> checkPoints) throws FileNotFoundException ;
	String getCodeFileSuffix();

	FileOutputStream generateCodeFile(String workDirectory,String fileName,long submissionId) throws FileNotFoundException;
	FileOutputStream generateINCheckPointsFile(CheckPoint checkPoint)throws FileNotFoundException;
	FileOutputStream generateOUTCheckPointsFile(CheckPoint checkPoint)throws FileNotFoundException;
}

