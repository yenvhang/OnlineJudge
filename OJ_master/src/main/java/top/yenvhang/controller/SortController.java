package top.yenvhang.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.tools.internal.xjc.model.CAdapter;

import top.yenvhang.controller.base.BaseController;
import top.yenvhang.model.Sort;
import top.yenvhang.model.Submission;
import top.yenvhang.model.User;
import top.yenvhang.service.SortService;
import top.yenvhang.util.LimitAttribute;
import top.yenvhang.util.LimitUtil;

@Controller
@RequestMapping(value ="/sort")
public class SortController extends BaseController {
	@Autowired
	SortService sortService;

	/**
	 * 排序显示用户总的加权值
	 */
	@RequestMapping(value ="/users", method = RequestMethod.GET)
	public String UsersView(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		LimitUtil limitUtil = new LimitUtil();
		long totals = getNumsofUsers();
		LimitAttribute limitAttribute = limitUtil.limit(model, totals, page, pageSize);
		List<User> users = sortService.getUsersUsingFilters(limitAttribute.startIndex, limitAttribute.pageSize);
		User user =getCurrentUser(request.getSession());
		if(user!=null){ 
			long rank =sortService.getRankUsingUserId(user.getUser_id());
			model.addAttribute("rank",rank);
		}
		
		model.addAttribute("users", users);
		
		return "sort/user/sort";
	}
	/**
	 * 排序显示用户指定题目的加权值
	 * @return
	 */
	@RequestMapping(value ="/problem/users", method = RequestMethod.GET)
	public String UsersView(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			@RequestParam(value="problem_id",required =true) int problemId,
			HttpServletRequest request, HttpServletResponse response) {
		LimitUtil limitUtil = new LimitUtil();
		long totals = getNumsOfACsubmissionUsingProblemId(problemId);
		User user =getCurrentUser(request.getSession());
		LimitAttribute limitAttribute = limitUtil.limit(model, totals, page, pageSize);
		List<Submission> submissions = sortService.getUsersACUsingProblemId(limitAttribute.startIndex,limitAttribute.pageSize,
				problemId);
		if(user!=null){
			long rank =sortService.getRankUsingProblemAndUserId(problemId,user.getUser_id());
			model.addAttribute("rank",rank);
		}
		model.addAttribute("problemId",problemId);
		model.addAttribute("submissions", submissions);
		return "sort/problem/users";
	}
	@RequestMapping(value="user/weekSort",method=RequestMethod.GET)
	public String getWeekSort(Date start,Date end,Model model){
		Calendar firstDay =Calendar.getInstance();
		Calendar lastDay =Calendar.getInstance();
		firstDay.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		lastDay.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		lastDay.add(Calendar.WEEK_OF_YEAR,1);
		 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Sort> sorts =sortService.getWeekSort(firstDay.getTime(), lastDay.getTime());
		model.addAttribute("sorts",sorts);
		
		return "sort/user/weekSort";
	}

	private long getNumsofUsers() {
		return sortService.getNumsofUsers();
	}
	private long getNumsOfACsubmissionUsingProblemId(long problemId){
		return sortService.getNumsOfACsubmissionUsingProblemId(problemId);
	}
	
	
}
