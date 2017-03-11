package top.yenvhang.model;

import java.sql.Date;

public class User {
	private long user_id;
	private String email;
	private String phone;
	private String ip;
	private String username;
	private String password;
	private String salt;
	private String school;
	private int submitted;
	private int solved;
	private int score;
	private String signature;
	
	
	public String getSalt() {
	
		return salt;
	}
	public void setSalt(String salt) {
	
		this.salt = salt;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	} 
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getSubmitted() {
		return submitted;
	}
	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}
	public int getSolved() {
		return solved;
	}
	public void setSolved(int solved) {
		this.solved = solved;
	}
	public User(){}
	public User(String email, String phone, String ip, String username, String password, String salt, String school) {
		this.email = email;
		this.phone = phone;
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.school = school;
	}
	
	
	
	
}
