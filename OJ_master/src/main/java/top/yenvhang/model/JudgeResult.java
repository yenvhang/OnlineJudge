package top.yenvhang.model;

public class JudgeResult {
	private int result_id;
	private String judgeResultName;
	
	public static final String COMPILING="正在编译"; 
	public static final String COMPILE_FAILED="编译错误";
	public static final String COMPILE_SUCCEED="编译通过";
	
	public static final String QUEUING="正在评判";
	public static final String AC="完全正确";
	public static final String PRESENTATION_ERROR="输出的格式不符合要求";
	public static final String WRONG_ANSWER="答案错误";
	public static final String RUNTIM_EERROR="运行错误";
	public static final String MEMORY_LIMIT="内存溢出";
	public static final String RUNTIME_LIMIT="运行超时";
	
	public static final String SYSTEM_ERROR="系统错误";
	public int getResult_id() {
		return result_id;
	}
	public void setResult_id(int result_id) {
		this.result_id = result_id;
	}
	public String getJudgeResultName() {
		return judgeResultName;
	}
	public void setJudgeResultName(String judgeResultName) {
		this.judgeResultName = judgeResultName;
	}

	
	
	
}
