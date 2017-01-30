package top.yenvhang.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 电子邮件验证
 * @author yenvhang
 *
 */
public class EmailValidation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String token;
	private Date expireTime;
	public EmailValidation() {
	}
	public EmailValidation(String email, String token, Date expireTime) {
		this.email = email;
		this.token = token;
		this.expireTime = expireTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
