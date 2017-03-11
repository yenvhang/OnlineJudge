
/**
* Project Name:OJ_master
* File Name:LoginTicket.java
* Package Name:top.yenvhang.model
* Date:2017年2月8日下午12:47:38
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.model;

import java.util.Date;

/**
* ClassName:LoginTicket <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月8日 下午12:47:38 <br/>
* @author 叶宇航
* @version
* @see
*/
public class LoginTicket {
	private long tid;
	private long user_id;
	private String ticket;
	private Date expired;
	private int status;
	public long getTid() {
	
		return tid;
	}
	public void setTid(long tid) {
	
		this.tid = tid;
	}
	public long getUser_id() {
	
		return user_id;
	}
	public void setUser_id(long user_id) {
	
		this.user_id = user_id;
	}
	public String getTicket() {
	
		return ticket;
	}
	public void setTicket(String ticket) {
	
		this.ticket = ticket;
	}
	public Date getExpired() {
	
		return expired;
	}
	public void setExpired(Date expired) {
	
		this.expired = expired;
	}
	public int getStatus() {
	
		return status;
	}
	public void setStatus(int status) {
	
		this.status = status;
	}
	public LoginTicket(){}
	
	public LoginTicket(long user_id, String ticket, Date expired, int status) {
		this.user_id = user_id;
		this.ticket = ticket;
		this.expired = expired;
		this.status = status;
	}
	
	
	
}

