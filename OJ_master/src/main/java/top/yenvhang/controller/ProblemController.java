package top.yenvhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import core.JudgeBin;
import top.yenvhang.controller.base.BaseController;
import top.yenvhang.model.Problem;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;
import top.yenvhang.service.ProblemService;
import top.yenvhang.service.SubmissionService;
import top.yenvhang.util.LimitAttribute;
import top.yenvhang.util.LimitUtil;
@Controller
@RequestMapping(value="/problems")
public class ProblemController extends BaseController {
	public final static String results[]={"完全正确","部分正确","答案错误","编译错误","运行错误","运行超时","内存溢出","系统错误"};
	@Autowired
	private ProblemService problemService;
	@Autowired
	private SubmissionService submissionService;
/**
 * 显示题目列表
 * @param model
 * @param page
 * @param numOfPerPage
 * @param request
 * @param response
 * @return
 */
	@RequestMapping(value ="", method = RequestMethod.GET)
	public String problemsView(
			Model model, 
			@RequestParam(value="page",required=false,defaultValue="1") int page,
			@RequestParam(value="pageSize",required=false,defaultValue="20") int pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		LimitUtil limitUtil =new LimitUtil();
		long totals = getNumsofProblems();
		LimitAttribute limitAttribute =limitUtil.limit(model, totals, page, pageSize);
		
		List<Problem> problems = problemService.getProblemsUsingFilters(
				limitAttribute.startIndex, limitAttribute.pageSize);

		model.addAttribute("list", problems);
		return "problems/problems";
	}
	
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public String insertAction(){
		return null;
	}
	/**
	 * 获取指定Id 的详细题目信息
	 */
	@RequestMapping(value="/{problem_id}",method=RequestMethod.GET)
	public String ProblemView(Model model,@PathVariable("problem_id") int problem_id,
			HttpServletRequest request, HttpServletResponse response){
		Problem problem =problemService.getProblemById(problem_id);
		model.addAttribute("problem",problem);
		model.addAttribute("problemId",problem_id);
		return "problems/problem";
		
	}
	/**
	 * 提交答案
	 * @param problem_id
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="/createSubmission.action",method=RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> createSubmission(Model model,
			@RequestParam("problem_id") int problem_id,
			@RequestParam("code") String code,
			HttpServletRequest request,HttpServletResponse response
			){
		HttpSession session =request.getSession();
		User user =getCurrentUser(session);
		Submission submission =submissionService.createSubmission(user,problem_id,code);
		Map<String,Object> result =new HashMap<String, Object>();
		result.put("msg",submission.getJudgeResult().getJudgeResultName());
		result.put("submissionId",submissionService.getLastSubmissionId());
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/solved",method=RequestMethod.POST)
	public Map<String,Object> getSolvedProblemIds(
			@RequestParam(value="userId")long userId){
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("ids",problemService.getSolvedProblemId(userId));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/beingsolved",method=RequestMethod.POST)
	public Map<String,Object> getBeingSolvedProbelmIds(
			@RequestParam(value="userId")long userId){
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("ids",problemService.getBeingSolvedProbelmId(userId));
		return result;
	}
/**
	 * 获取试题的起始编号.
	 * @return 试题的起始编号
	 */
	private long getFirstIndexOfProblems() {
		return problemService.getFirstIndexOfProblems();
	}
	/**
	 * 获取试题个数
	 * @return
	 */
	private int getNumsofProblems(){
		return problemService.getNumsofProblems();
	}
	
	
	
	
}
