
/**
* Project Name:OJ_master
* File Name:WebConfiguration.java
* Package Name:top.yenvhang.configuration
* Date:2017年2月8日下午3:51:06
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import top.yenvhang.intercepter.PassportInterceptor;

/**
* ClassName:WebConfiguration <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月8日 下午3:51:06 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class WebConfiguration extends WebMvcConfigurerAdapter{
	@Autowired
	PassportInterceptor passportInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(passportInterceptor);
		super.addInterceptors(registry);
		
	}
	
}

