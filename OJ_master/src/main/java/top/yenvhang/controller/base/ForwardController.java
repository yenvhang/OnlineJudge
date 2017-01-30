package top.yenvhang.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//RESTful风格的URL参数
public class ForwardController {
	
	@RequestMapping("/forward")
	public ModelAndView forward(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(page);

		return modelAndView;
	}
	
}
