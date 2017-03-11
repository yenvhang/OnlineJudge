package top.yenvhang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import top.yenvhang.controller.base.BaseController;
import top.yenvhang.model.Comment;
import top.yenvhang.model.HostHolder;
import top.yenvhang.model.Message;
import top.yenvhang.model.MultipleComment;
import top.yenvhang.model.User;
import top.yenvhang.service.MessageService;

@Controller
@RequestMapping(value="/message")
public class MessageController  {
	@Autowired
	MessageService messageService;
	@RequestMapping(value="/personalMessage")
	public String showPersonalMessageView(Model model,
			HttpServletRequest request){
		User user =HostHolder.getUser();
		List<Message> convensations = null;
		if(user!=null){
			long userId =user.getUser_id();
			convensations =messageService.getConvensationList(0, 20, userId);
			
		}
		model.addAttribute("convensations",convensations);
		return "message/personalMessage";
	}
	@RequestMapping(value="/convensationDetail",method=RequestMethod.GET)
	public String getConvensationDeatail(Model mode,
			@RequestParam(value="convensationId") long convensationId,
			HttpServletRequest request){
		List<Message> messages =messageService.getConvensationDetail(0, 20, convensationId);
		mode.addAttribute("messages",messages);
		return "message/convensationDetail";
	}
	
	@RequestMapping(value="/systemMessage")
	public String showSystemMessgaeView(HttpServletRequest request,Model model){
		User user =HostHolder.getUser();
		List<Comment> comments=messageService.getNewComments(user.getUser_id());
		model.addAttribute("comments", comments);
		return "message/systemMessage";
	}
	
	@RequestMapping(value="/systemMessageDetail")
	public String getMessageDeatail(HttpServletRequest request,Model model,
			@RequestParam(value="commentId") long commentId){
		List<MultipleComment> multipleComments=messageService.getMultipleComment(commentId);
		messageService.readComment(new Comment(commentId, true));
		model.addAttribute("multipleComments", multipleComments);
		return "message/systemMessageDetail";
	}
}
