package top.yenvhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.yenvhang.controller.base.BaseController;
import top.yenvhang.mapper.CommentMapper;
import top.yenvhang.model.Comment;
import top.yenvhang.model.EntityType;
import top.yenvhang.model.User;
import top.yenvhang.service.AccountService;
import top.yenvhang.service.DiscussService; 

@Controller
@RequestMapping(value="/discuss")
public class DiscussController extends BaseController {
	@Autowired
	CommentMapper commentMapper;
	@Autowired 
	AccountService accountService;
	@Autowired
	DiscussService discussService;
	@RequestMapping(value="/problem/comment",method=RequestMethod.GET)
	public String getProblemComments(Model model,
			@RequestParam(value="problem_id") long problemId,
			HttpServletRequest request,HttpServletResponse response){
		List<Comment> comments =commentMapper.getCommentsByEntityId(problemId, EntityType.PROBLEM_ID);
		for(Comment comment:comments){
			comment.setReplys(commentMapper.getCommentsByEntityId(comment.getComment_id(),EntityType.REPLY_ID));
		}
		model.addAttribute("comments", comments);
		model.addAttribute("problemId",problemId);
		return "discuss/problem";
	}
	
	@ResponseBody
	@RequestMapping(value="/problem/addComment",method=RequestMethod.POST)
	public Map<String,Object> addProblemComments(
			@RequestParam(value="problem_id") long problemId,
			@RequestParam(value="content") String content,
			@RequestParam(value="to_id",required=false,defaultValue="0") long to_id,
			HttpServletRequest request,HttpServletResponse response){
		User user =getCurrentUser(request.getSession());
		if(user!=null){
			Comment comment=new Comment();
			comment.setEntity_id(problemId);
			comment.setEntity_type(EntityType.PROBLEM_ID);
			comment.setContent(content);
			comment.setFrom(user);
			comment.setTo(accountService.getUserUsingUser_id(to_id));
			commentMapper.createComment(comment);
		}
		Map<String,Object> result =new HashMap<String, Object>();
		result.put("IsOk", true);
	
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/problem/addChildComment",method=RequestMethod.POST)
	public Map<String,Object> addChildProblemComments(
			@RequestParam(value="comment_id") long commentId,
			@RequestParam(value="content") String content,
			@RequestParam(value="to_id") long to_id,
			HttpServletRequest request,HttpServletResponse response){
		User user =getCurrentUser(request.getSession());
		if(user!=null){
			Comment comment=new Comment();
			comment.setEntity_id(commentId);
			comment.setEntity_type(EntityType.REPLY_ID);
			comment.setContent(content);
			comment.setFrom(user);
			comment.setTo(accountService.getUserUsingUser_id(to_id));
			commentMapper.createComment(comment);
		}
		Map<String,Object> result =new HashMap<String, Object>();
		result.put("IsOk", true);
	
		return result;
	}
	
	@RequestMapping(value="/personal/sendMessage",method=RequestMethod.GET)
	public String showsendMessageView(@RequestParam(value="toId") long toId,
			Model model,HttpServletRequest request,HttpServletResponse response){
			User user =accountService.getUserUsingUser_id(toId);
			model.addAttribute("toUser",user);
		return "personal/sendMessage";
	}
	@ResponseBody
	@RequestMapping(value="personal/sendMessage.action",method=RequestMethod.POST)
	public Map<String,Object> sendMessage(@RequestParam(value="content") String content,
			@RequestParam(value="toId")long toId,
			HttpServletRequest request){
		Map<String,Object>result=new HashMap<String, Object>();
		User user =getCurrentUser(request.getSession());
		discussService.sendMessage(user.getUser_id(), toId, content);
		result.put("sendSuccess",true);
		return result;
	}
			
	
	
	
	
}

