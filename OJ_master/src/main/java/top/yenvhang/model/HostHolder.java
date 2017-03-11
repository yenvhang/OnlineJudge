
/**
* Project Name:OJ_master
* File Name:HostHolder.java
* Package Name:top.yenvhang.model
* Date:2017年2月8日下午2:37:39
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.model;

import org.springframework.stereotype.Component;

/**
* ClassName:HostHolder <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月8日 下午2:37:39 <br/>
* @author 叶宇航
* @version
* @see
*/
@Component
public class HostHolder {
	private static ThreadLocal<User> users=new ThreadLocal<User>();
	public static void setUser(User user){
		users.set(user);
	}
	public static User getUser(){
		return users.get();
	}
	public static void clear(){
		users.remove();
	} 
}

