
/**
* Project Name:OJ_master
* File Name:InterceptorAspect.java
* Package Name:top.yenvhang.aspect
* Date:2017年2月6日下午2:08:42
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import top.yenvhang.model.HostHolder;

/**
* ClassName:InterceptorAspect <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月6日 下午2:08:42 <br/>
* @author 叶宇航
* @version
* @see
*/
@Aspect
public class InterceptorAspect {
	
	@Around(value = "execution(* top.yenvhang.controller.SubmissionController.querySubmissionsByProblemId(..)) && args(..,request)")
	public String Login(ProceedingJoinPoint proceedingJoinPoint,HttpServletRequest request) throws Throwable {
		if(HostHolder.getUser()==null) {
			return "account/login";
		}
		else{
			return (String) proceedingJoinPoint.proceed();
		}
	
	}
}

