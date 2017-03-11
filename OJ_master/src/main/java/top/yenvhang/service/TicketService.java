
/**
* Project Name:OJ_master
* File Name:TicketService.java
* Package Name:top.yenvhang.service
* Date:2017年2月8日下午1:38:37
* Copyright (c) 2017, 叶宇航 All Rights Reserved.
*
*/

package top.yenvhang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.mapper.LoginTicketMapper;
import top.yenvhang.model.LoginTicket;

/**
* ClassName:TicketService <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月8日 下午1:38:37 <br/>
* @author 叶宇航
* @version
* @see
*/
@Service
public class TicketService {
	@Autowired
	public LoginTicketMapper loginTickerMapper;
	public int createLoginTicket(LoginTicket loginTicket){
		return loginTickerMapper.addTicket(loginTicket);
	}
	public LoginTicket getLoginTicket(String ticket){
		return loginTickerMapper.selectTicketByTicket(ticket);
	}
	public void logout(String ticket){
		loginTickerMapper.updateTicketStatus(ticket, 0);
	}
}

