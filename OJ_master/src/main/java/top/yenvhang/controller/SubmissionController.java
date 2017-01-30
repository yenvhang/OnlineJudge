package top.yenvhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.tools.internal.ws.processor.generator.GeneratorUtil;

import top.yenvhang.controller.base.BaseController;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;
import top.yenvhang.service.CodeService;
import top.yenvhang.service.RunInfoService;
import top.yenvhang.service.SubmissionService;
import top.yenvhang.util.LimitAttribute;
import top.yenvhang.util.LimitUtil;

@Controller
@RequestMapping(value = "/submissions")
public class SubmissionController extends BaseController {
	@Autowired
	SubmissionService submissionService;
	@Autowired
	RunInfoService runInfoService;
	@Autowired
	CodeService codeService;
	/**
	 * 显示提交列表
	 * @param model
	 * @param page
	 * @param pageSize
	 * @param request
	 * @param judgeResultName
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String submissionsView(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize, HttpServletRequest request,
			@RequestParam(value="result",required=false) String judgeResultName,
			HttpServletResponse response) {
		LimitUtil limitUtil =new LimitUtil();
		long totals =getNumsofSubmissions();
		LimitAttribute limitAttribute =limitUtil.limit(model, totals, page, pageSize);
		List<Submission> submissions = submissionService.getSubmissionsUsingFilter(limitAttribute.startIndex, 
				limitAttribute.pageSize,judgeResultName);
		model.addAttribute("submissions", submissions);
		return "submission/submissions";
	}
	@RequestMapping(value="/querySubmission/p",method=RequestMethod.GET)
	public String querySubmissionsByProblemId(Model model,long problem_id,
			HttpServletRequest request){
		User user =getCurrentUser(request.getSession());
		
		List<Submission> submissions =submissionService.getSubmissionUsingUserAndProblem_id(user.getUser_id(), problem_id);
		model.addAttribute("submissions", submissions);
		model.addAttribute("problemId",problem_id);
		return "submission/mysubmission";
	};
	@RequestMapping(value="/querySubmission/s",method=RequestMethod.GET)
	public String querySubmissionsBySubmissionId(Model model,long submission_id,
			HttpServletRequest request){
		User user =getCurrentUser(request.getSession());
		List<Submission> submissions =submissionService.getSubmissionUsingSubmissionId(submission_id);
		model.addAttribute("submissions", submissions);
		return "submission/mysubmission";
	};
	
	
	
	
	
	
	/**
	 *获取用户提交的源代码 
	 */
	
	@RequestMapping(value="/queryCode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> queryCode(@RequestParam(value="submission_id")long submissionId){
		Map<String,String> result =new HashMap<String, String>();
		String code=codeService.getCodeBySubmissionId(submissionId);
		result.put("code",code);
		return result;
	}
	
	
	/**
	 * 获取评测信息
	 */
	
	@RequestMapping(value="/queryRunInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> queryRunInfo(@RequestParam(value="submission_id")long submissionId){
		Map<String,String> result =new HashMap<String, String>();
		String runInfo=runInfoService.getRunInfoBySubmissionId(submissionId);
		result.put("runInfo",runInfo);
		
		return result;
	}
	
	/**
	 * 
	 */
	
	
	/**
	 * 获取submission 总数
	 * @return
	 */
	private long getNumsofSubmissions() {
		
		return submissionService.getNumsOfSubmissions();
	}
}
