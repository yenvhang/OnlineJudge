
/**
* Project Name:OJ_master
* File Name:LoginTicketMapper.java
* Package Name:top.yenvhang.mapper
* Date:2017年2月8日下午12:54:14
* Copyright (c) 2017, Tony All Rights Reserved.
*
*/

package top.yenvhang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import top.yenvhang.model.LoginTicket;

/**
* ClassName:LoginTicketMapper <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年2月8日 下午12:54:14 <br/>
* @author 叶宇航
* @version
* @see
*/
public interface LoginTicketMapper {
	String TABLE_NAME="oj_login_ticket";
	String INSERT_FIELDS="user_id,expired,status,ticket";
	String SELECT_FIELDS="tid,"+INSERT_FIELDS;
	
	@Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,")values(#{user_id},#{expired},#{status},#{ticket})"})
	public int addTicket(LoginTicket loginTicket);
	
	@Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where ticket=#{ticket}"})
	public LoginTicket selectTicketByTicket(String ticket);
	
	@Update({"update",TABLE_NAME,"set status=#{status} where ticket=#{ticket}"})
	public void updateTicketStatus(@Param("ticket")String ticket,@Param("status")int status);
}	

