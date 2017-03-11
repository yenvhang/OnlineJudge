//package top.yenvhang.test;
//import java.util.Date;
//import java.util.Random;
//import java.util.Scanner;
//import java.util.UUID;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import top.yenvhang.mapper.JudgeResultMapper;
//import top.yenvhang.mapper.SubmissionMapper;
//import top.yenvhang.mapper.UserMapper;
//import top.yenvhang.model.JudgeResult;
//import top.yenvhang.model.Problem;
//import top.yenvhang.model.Submission;
//import top.yenvhang.model.User;
//import top.yenvhang.service.ProblemService;
//import top.yenvhang.util.DigestUtils;
//@RunWith(SpringJUnit4ClassRunner.class)  
//@ContextConfiguration   
//({"classpath*:/dispatcher-servlet.xml"})
//
//public class TestProblemMapper {
//	@Autowired
//	ProblemService problemService;
//	@Autowired
//	JudgeResultMapper judgeResultMapper;
//	@Autowired
//	SubmissionMapper submissionMapper;
//	@Autowired
//	UserMapper userMapper;
//	public final static String Authors [] ={"admin","creep","yeyuhang","leetcode"};
//	public final static String levels[]={"easy","mid","tough"};
//	public final static String result[]={"完全正确","部分正确","答案错误","编译错误","运行错误","运行超时","内存溢出","系统错误"};
//	@Test
//	public void insert(){
//		Scanner sc =new Scanner(System.in);
//		Date date =new Date();
//		for(int i=0;i<400;i++){
//			Random randow =new Random();
//			Problem problem =new Problem();
//			problem.setJoin_date(date);
//			problem.setTitle("A+B problem"+i);
//			problem.setContent("此题为练手用题，请大家计算一下a+b的值.");
//			problem.setInput("输入两个数，a,b");
//			problem.setOutput("输出a+b的值");
//			problem.setInput_sample("2 3");
//			problem.setOutput_sample("5");
//			problem.setHint("Java版："+
//"public class Main"+
//"{"+
//"public static void main(String args[]) throws Exception"+
//"{"+
//"Scanner cin=new Scanner(System.in);"+
//"int a=cin.nextInt(),b=cin.nextInt();"+
//"System.out.println(a+b);"+
//"}"+
//"}");
//			problem.setScore(randow.nextInt(20));
//			problem.setMemory_limit(randow.nextInt(200));
//			problem.setRuntime_limit(randow.nextInt(4000));
//			problem.setLevel(levels[randow.nextInt(2)]);
//			problem.setAuthor(Authors[randow.nextInt(3)]);
//		problemService.insertProblem(problem);
//		}
//		
//	}
//	@Test
//	public void insertJudgeResult(){
//		for(int i=0;i<=7;i++){
//			JudgeResult judgeResult =new JudgeResult();
//			judgeResult.setResult_id(i+1);
//			judgeResult.setJudgeResultName(result[i]);
//			judgeResultMapper.InsertJugeResult(judgeResult);
//		}
//	}
//	
////	public void createSubmission(){
////		Random random =new Random();
////		User user =new User();
////		user.setUser_id(1);
////		user.setUsername("creep");
////		for(int i=0;i<1000000;i++){
////			Submission submission =new Submission(random.nextInt(1500), user, new Date(), "Java版："+
////					"public class Main"+
////					"{"+
////					"public static void main(String args[]) throws Exception"+
////					"{"+
////					"Scanner cin=new Scanner(System.in);"+
////					"int a=cin.nextInt(),b=cin.nextInt();"+
////					"System.out.println(a+b);"+
////					"}"+
////					"}");
////			submissionMapper.insertSubmission(submission);
////		}
////	}
//	@Test
//	public void updateSubmission(){
//		Random random =new Random();
//		for(int i=0;i<1000000;i++){
//			Submission submission = null;
////					submissionMapper.getSubmissionUsingSubmission_id(random.nextInt(1000));
//			if(submission!=null){
//				submission.setExecuteTime(new Date());
//				int num =random.nextInt(7);
//				JudgeResult judgeResult =new JudgeResult();
//				judgeResult.setResult_id(num+1);
//				judgeResult.setJudgeResultName(result[num]);
//				submission.setJudgeResult(judgeResult);
//				submission.setUsememory(random.nextInt(200));
//				submission.setUseTime(random.nextInt(200));
//				submission.setJudgeScore(random.nextInt(10));
//				submissionMapper.updateSubmission(submission);
//			}
//		}
//	}
//	
//	public void getSubmissions(){
//		
//	}
//	
//	@Test
//	public void getJudgeResult(){
//		System.out.println(judgeResultMapper.getJudgeResultUsingName("完全正确").getResult_id());
//	
//	}
//	
//	
//	@Test
//	public void inserUser(){
//		for(int i=0;i<100000;i++){
//			Random random =new Random();
//			User user =new User();
//			user.setUsername(UUID.randomUUID().toString());
//			user.setEmail(UUID.randomUUID().toString());
//			user.setPassword("123456");
//			user.setSignature("楼上傻逼");
//			user.setScore(random.nextInt(200));
//			user.setSolved(random.nextInt(100));
//			user.setSubmitted(random.nextInt(500));
//			userMapper.insertUser(user);
//		}
//	}
//	@Test
//	public void getPassword(){
//		
//		System.out.println(DigestUtils.md5Hex("123456"));
//	}
//	
//	
//	
//}
