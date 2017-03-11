
/**
* Project Name:OJ_master
* File Name:HomeController.java
* Package Name:top.yenvhang.controller
* Date:2017年2月8日下午9:47:56
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* ClassName:HomeController <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月8日 下午9:47:56 <br/>
* @author 叶宇航
* @version
* @see
*/
@Controller
@RequestMapping(value="/")
public class HomeController {
	@RequestMapping(value="",method=RequestMethod.GET)
	public String indexView(){
		return "index";
	}
	
}

