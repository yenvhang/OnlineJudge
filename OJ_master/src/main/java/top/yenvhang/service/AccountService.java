package top.yenvhang.service;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.dao.AccountDAO;
import top.yenvhang.dao.EmailValidatorDAO;
import top.yenvhang.mapper.UserMapper;
import top.yenvhang.model.EmailValidation;
import top.yenvhang.model.Problem;
import top.yenvhang.model.User;
import top.yenvhang.util.DigestUtils;
import top.yenvhang.util.MailSender;
@Service
public class AccountService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	EmailValidatorDAO emailValidatorDAO;
	
	public Map<String,Boolean> isAllowedToLogin(String username,String password){
		Map<String,Boolean> result =new HashMap<String, Boolean>();
		result.put("isUsernameEmpty",username.isEmpty());
		result.put("isPasswordEmpty",password.isEmpty());
		if(!result.get("isUsernameEmpty")&&!result.get("isPasswordEmpty")){
			long startTime =System.currentTimeMillis();
			System.out.println("start:"+startTime);
			User user =getUserUsingUsernameOrEmail(username);
			if(user!=null&&user.getPassword().equals(DigestUtils.md5Hex(password))){
				result.put("isSuccessful", true);
				long end =System.currentTimeMillis();
				System.out.println("end:"+(end-startTime));
				return result;
			}
			
			result.put("callback", true);
			result.put("isSuccessful", false);
			result.put("getIsUserExists", user!=null);
		
			
		}
		return result;
	}
	
	public User getUserUsingUsernameOrEmail(String name){
		boolean isUsingEmail=name.indexOf('@')!=-1;
		User user =null;
		if(isUsingEmail){
			user =userMapper.getUserUsingEmail(name);
		}
		else{
			user=userMapper.getUserUsingUsername(name);
		}
		return user;
	}
	
	public Map<String,Boolean> createUser(String username,String password,String email,
			String phone,String school){
		Map<String,Boolean> result =
				getUserCreationResult(username, password, email, phone, school);
		if(result.get("isSuccessful")){
//			accountDao.createUser(username, password, email, phone, school);
		}
		
		return result;
	}
	
	private Map<String, Boolean> getUserCreationResult(String username,String password,String email,
			String phone,String school) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("isUsernameEmpty", username.isEmpty());
		result.put("isUsernameLegal", isUsernameLegal(username));
		result.put("isUsernameExists", isUsernameExists(username));
		result.put("isPasswordEmpty", password.isEmpty());
		result.put("isPasswordLegal", isPasswordLegal(password));
		result.put("isEmailEmpty", email.isEmpty());
		result.put("isEmailLegal", isEmailLegal(email));
		result.put("isEmailExists", isEmailExists(email));
		boolean isSuccessful=false;
		isSuccessful= !result.get("isUsernameEmpty")  &&  result.get("isUsernameLegal")  &&
							   !result.get("isUsernameExists") && !result.get("isPasswordEmpty")  &&
								result.get("isPasswordLegal")  && !result.get("isEmailEmpty")	 &&
								result.get("isEmailLegal")	 && !result.get("isEmailExists")	&&
		result.put("isSuccessful", isSuccessful);
		return result;
	}

	/**
	 * 验证用户名的合法性:
	 * 规则: 用户名应由[A-Za-z0-9_]组成, 以字母起始且长度在6-16个字符.
	 * @param username - 用户名
	 * @return 用户名是否合法
	 */
	private boolean isUsernameLegal(String username) {
		return username.matches("^[A-Za-z][A-Za-z0-9_]{5,15}$");
	}
	
	/**
	 * 检查用户名是否存在.
	 * @param username - 用户名
	 * @return 用户名是否存在
	 */
	private boolean isUsernameExists(String username) {
		User user = userMapper.getUserUsingUsername(username);
		return user != null;
	}
	
	/**
	 * 检查密码是否合法.
	 * 规则: 密码的长度在6-16个字符.
	 * @param password - 密码(未经MD5加密)
	 * @return 密码是否合法
	 */
	private boolean isPasswordLegal(String password) {
		int passwordLength = password.length();
		return passwordLength >= 6 && passwordLength <= 16;
	}
	
	/**
	 * 更改密码时, 验证用户的旧密码是否正确.
	 * @param oldPassword - 用户的旧密码(已使用MD5加密)
	 * @param submitedPassword - 所提交进行验证的旧密码(未使用MD5加密)
	 * @return 用户旧密码是否正确
	 */
	private boolean isOldPasswordCorrect(String oldPassword, String submitedPassword) {
		if ( submitedPassword.isEmpty() ) {
			return true;
		}
		return oldPassword.equals(DigestUtils.md5Hex(submitedPassword));
	}
	
	/**
	 * 检查电子邮件地址是否合法.
	 * 规则: 合法的电子邮件地址且长度不超过64个字符.
	 * @param email - 电子邮件地址
	 * @return 电子邮件地址是否合法
	 */
	private boolean isEmailLegal(String email) {
		int emailLength = email.length();
		return emailLength <= 64 && email.matches("^[A-Za-z0-9\\._-]+@[A-Za-z0-9_-]+\\.[A-Za-z0-9\\._-]+$");
	}
	
	/**
	 * 检查电子邮件地址是否存在.
	 * 说明: 仅用于用户创建新账户
	 * @param email - 电子邮件地址
	 * @return 电子邮件地址是否存在
	 */
	private boolean isEmailExists(String email) {
		User user = userMapper.getUserUsingEmail(email);
		return user != null;
	}
	
	/**
	 * 检查电子邮件地址是否存在.
	 * 说明: 仅用于用户编辑个人资料
	 * @param currentEmail - 之前所使用的Email地址
	 * @param email - 待更新的Email地址
	 * @return 电子邮件地址是否存在
	 */
	private boolean isEmailExists(String currentEmail, String email) {
		if ( currentEmail.equals(email) ) {
			return false;
		}
		User user = userMapper.getUserUsingEmail(email);
		return user != null;
	} 

	public boolean isEmailValidationValid(String email, String token) {
		Date now =new Date();
		EmailValidation emailValidation =emailValidatorDAO.getEmailValidation(email);
		if(emailValidation.getToken().equals(token)&&
				now.before(emailValidation.getExpireTime())){
			return true;
		}
		
		return false;
	}

	public Map<String, Boolean> resetPassword(String email, String token, String newPassword, String confirmPassword) {
		Map<String,Boolean> result =new HashMap<String, Boolean>();
		result=getResetPasswordResult(email, token, newPassword, confirmPassword);
		
		if(result.get("isSuccessful")){
			User user =getUserUsingUsernameOrEmail(email);
			user.setPassword(DigestUtils.md5Hex(newPassword));
//			userMapper.updateUser(user);
		}
		return result;
	}
	
	public Map<String, Boolean> getResetPasswordResult(String email, String token, String newPassword, String confirmPassword) {
		Map<String,Boolean> result =new HashMap<String, Boolean>();
		result.put("isEmailValidationValid", isEmailValidationValid(email,token));
		result.put("isNewPasswordEmpty", newPassword.isEmpty());
		result.put("isNewPasswordLegal", isPasswordLegal(newPassword));
		result.put("isConfirmPasswordMatched", newPassword.equals(confirmPassword));
		boolean isSuccessful =  result.get("isEmailValidationValid") &&
				   !result.get("isNewPasswordEmpty")	 && result.get("isNewPasswordLegal") &&
					result.get("isConfirmPasswordMatched");
		result.put("isSuccessful", isSuccessful);
		return result;
	}
	/**
	 * 验证邮箱账号有效性并发送邮箱
	 * @param email
	 * @param username
	 * @return
	 */
	public Map<String, Boolean> sendVerificationEmail(String email, String username) {
		Map<String,Boolean> result =new HashMap<String, Boolean>();
		User user =userMapper.getUserUsingUsername(username);
		boolean isUserExists =false;
		
		if(user!=null&&user.getEmail().equals(email)){
		
			isUserExists=true;
			String token =DigestUtils.getGuid();
			Map<String,Object> model=new HashMap<String, Object>();
			model.put("email",email);
			model.put("username",username);
			model.put("token", token);
			
			Date expireTime =getExpireTime();
			EmailValidation emailValidation =new EmailValidation(email,token,expireTime);
			String subject="在线评测系统重置密码";
			String body ="您的账号"+username+"请求重置密码\n"+"你的验证码为"+token+"有效期为一小时";
			Message msg =MailSender.createSimpleMail(email, subject, body);
			MailSender.sendMessage(msg);
			
			
			
		}
		result.put("isUserExists", isUserExists);
		result.put("isSuccessful", isUserExists);
		return result;
	}
	
	public int updateUserMessgae(long user_id,int submitted,int solved,int score){
		return userMapper.updateUserMessage(user_id, submitted, solved,score);
	}

	private Date getExpireTime() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
	}

	public User getUserUsingUser_id(long user_id) {
		return userMapper.getUserUsingUser_id(user_id);
	
	}

	
	
}
