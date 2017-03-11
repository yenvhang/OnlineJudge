
/**
* Project Name:OJ_master
* File Name:PassportInterceptor.java
* Package Name:top.yenvhang
* Date:2017年2月8日下午2:42:09
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.intercepter;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import top.yenvhang.model.HostHolder;
import top.yenvhang.model.LoginTicket;
import top.yenvhang.model.User;
import top.yenvhang.service.AccountService;
import top.yenvhang.service.TicketService;

/**
* ClassName:PassportInterceptor <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月8日 下午2:42:09 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class PassportInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	TicketService ticketService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getCookies()!=null){
			for(Cookie cookie:request.getCookies()){
				if(cookie.getName().equals("loginticket")){
					String ticket =cookie.getValue();
					LoginTicket loginTicket =ticketService.getLoginTicket(ticket);
					if(loginTicket!=null&&loginTicket.getStatus()==1&&loginTicket.getExpired().after((new Date()))){
						User user=accountService.getUserUsingUser_id(loginTicket.getUser_id());
						HostHolder.setUser(user);
						break;
					}
				}
			}
		}
			return true;
		
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(modelAndView!=null&&HostHolder.getUser()!=null){ 
			modelAndView.addObject("user",HostHolder.getUser());
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			HostHolder.clear();
	}

}

