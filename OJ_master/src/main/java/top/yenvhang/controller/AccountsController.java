package top.yenvhang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.yenvhang.controller.base.BaseController;
import top.yenvhang.model.Problem;
import top.yenvhang.model.User;
import top.yenvhang.service.AccountService;
import top.yenvhang.util.LimitAttribute;
import top.yenvhang.util.LimitUtil;



@Controller
@RequestMapping(value="/accounts")
public class AccountsController extends BaseController {
	@Autowired
	AccountService accountService;
	/**
	 * 显示用户登录界面　处理用户注销
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginView(
			@RequestParam(value="logout",required =false,defaultValue="false")
			boolean isLogout,
			HttpServletRequest request,HttpServletResponse response){
			HttpSession session =request.getSession();
			ModelAndView view =null;
			if(isLogout){
				destorySession(request, session);
				
			}
			//如果用户已经登录
		
			view =new ModelAndView("account/login");
				
			
			return view;
		
	}
	
		/**
		 * 处理用户的登录请求
		 * @param username
		 * @param password
		 * @param isAutoLoginAllowed
		 * @param request
		 * @param response
		 * @return 返回请求结果
		 */
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public ModelAndView  loginAction(
				@RequestParam(value="username",required=true)
				String username,
				@RequestParam(value="password",required=true)
				String password,
				@RequestParam(value="rememberMe",required=false)
				boolean isAutoLoginAllowed,
				HttpServletRequest request){
			ModelAndView model=new ModelAndView();
			Map<String,Boolean> result =accountService.isAllowedToLogin(username, password);
			//创建用户session 
		 if(result!=null&&result.get("isSuccessful")){
			 User user =accountService.getUserUsingUsernameOrEmail(username);
			 createUserSession(request, user, isAutoLoginAllowed);
			 model.setViewName("redirect:/problems");
		 }
		 else{
			 model.setViewName("/account/login");
		 }
		 model.addObject("result", result);
		 return model;
	}
	/**
	 * 显示注册页面
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView registerView(){
		return new ModelAndView("accounts/register");
	}
	/**
	 * 处理用户注册请求
	 * @param username
	 * @param password
	 * @param email
	 * @param school
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/register.action",method=RequestMethod.POST)
	public @ResponseBody Map<String,Boolean> registerAction(
			@RequestParam(value="username",required=true)
			String username,
			@RequestParam(value="password",required=true)
			String password,
			@RequestParam(value="email",required=true)
			String email,
			@RequestParam(value="school",required=true)
			String school,
			@RequestParam(value="phone",required=true)
			String phone,
			HttpServletRequest request){
		 Map<String,Boolean> result =new HashMap<String, Boolean>();
		 result =accountService.createUser(username, password, email, phone, school);
		 if(result!=null&&result.get("isSuccessful")){
			 User user =accountService.getUserUsingUsernameOrEmail(username);
			 createUserSession(request, user, false);
		 }
		return result;
	}
	/**
	 * 加载修改密码页面
	 */
	
	@RequestMapping(value="/reset-password",method=RequestMethod.GET)
	public ModelAndView resetPasswordView(
			@RequestParam(value="email",required=true)
			String email,
			@RequestParam(value="token",required=true)
			String token,
			HttpServletRequest request, HttpServletResponse response){
			HttpSession session =request.getSession();
		ModelAndView view=null;
		if ( isLogin(session) ) {
			view = new ModelAndView("redirect:/");
		} else {
			boolean isTokenValid = false;
			if ( token != null && !token.isEmpty() ) {
				isTokenValid = accountService.isEmailValidationValid(email, token);
			}
			
			view = new ModelAndView("accounts/reset-password");
			view.addObject("email", email);
			view.addObject("token", token);
			view.addObject("isTokenValid", isTokenValid);
		}
		return view;
	}
	
	/**
	 * 重置用户密码
	 */
	@RequestMapping(value = "/reset-password.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> resetPasswordAction(
			@RequestParam(value = "email", required = true) 
			String email,
			@RequestParam(value = "token", required = true) 
			String token,
			@RequestParam(value = "newPassword", required = true) 
			String newPassword,
			@RequestParam(value = "confirmPassword", required = true) 
			String confirmPassword,
			HttpServletResponse request, HttpServletResponse response){
			
		Map<String,Boolean> result =new HashMap<String, Boolean>();
		result =accountService.resetPassword(email,token,newPassword,confirmPassword);
		
		return result;
	
	}
	
	/**
	 * 发送重置密码的邮件
	 */
	@RequestMapping(value="/send-resetPasswordEmail.action",method=RequestMethod.POST)
	public @ResponseBody Map<String,Boolean> sendResetPasswordEmail(
			@RequestParam(value="email",required=true)
			String email,
			@RequestParam(value="username",required=true)
			String username,
			HttpRequest request){
		Map<String,Boolean> result =accountService.sendVerificationEmail(email,username);
		
		return result;
	}
	

	
	
	
	/**
	 * 创建用户Sesison
	 * @param request
	 * @param user
	 * @param isAutoLoginAllowed
	 */
	
	/**
	 * 修改用户成绩
	 * @param request
	 * @param user
	 * @param isAutoLoginAllowed
	 */
	
	private void createUserSession(HttpServletRequest request,User user,
			boolean isAutoLoginAllowed){
		HttpSession session =request.getSession();
		session.setAttribute("user",user);
		session.setAttribute("isUserLoginAllowed", isAutoLoginAllowed); 
	
	}
	
	public User getUserUsingUser_id(long user_id){
		return accountService.getUserUsingUser_id(user_id);
	}
	
	/**
	 * 显示用户资料
	 * 
	 * 
	 */
	@RequestMapping(value="/userDetail",method=RequestMethod.GET)
	public String showUserDetailView(
			@RequestParam(value="userId",required=false,defaultValue="0")  long userId,
			Model model,HttpServletRequest request,HttpServletResponse response){
		User user=null;
		
		if(userId==0){
			user =getCurrentUser(request.getSession());
		}
		else{
			user =accountService.getUserUsingUser_id(userId);
		}
		
		model.addAttribute("tempuser",user);
		return "account/personal";
	}
	
	
	
	
}
