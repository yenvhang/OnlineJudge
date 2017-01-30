package top.yenvhang.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import top.yenvhang.model.Problem;
import top.yenvhang.model.User;
@Controller
public class BaseController {
	/**
	 * 检查用户的登录状态
	 * @param session
	 * @return
	 */
	protected boolean isLogin(HttpSession session){
		Boolean isLogin=(Boolean) session.getAttribute("isLogin");
				if(isLogin==null||!isLogin){
					return false;
				}
		return true;
	}
	
	protected void destorySession(HttpServletRequest request,HttpSession session) {
		session.setAttribute("user",null);
	}
	public User getCurrentUser(HttpSession session){
		return (User) session.getAttribute("user");
	}
	public Problem getCurrenProblem(HttpSession session){
		return (Problem) session.getAttribute("problem");
	}
}
