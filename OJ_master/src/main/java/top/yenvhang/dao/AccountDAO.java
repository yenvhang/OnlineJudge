package top.yenvhang.dao;

import org.springframework.stereotype.Repository;

import top.yenvhang.model.User;
import top.yenvhang.util.DigestUtils;
@Repository
public class AccountDAO {
	public User getUserUsingUsername(String name){
		return null;
	}
	public User getUserUingEmail(String name){
		return null;
	}
	public User createUser(String username,String password,String email,
			String phone,String school){
		password =DigestUtils.md5Hex(password);
		return null;
	}
	public void updateUser(User user) {
		
	}
}
