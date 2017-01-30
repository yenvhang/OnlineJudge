package top.yenvhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import top.yenvhang.model.User;

public interface UserMapper {
	/**
	 * 根据用户名称获取用户
	 * @param username
	 * @return
	 */
	public User getUserUsingUsername(@Param("username")String username);
	/**
	 * 根据用户邮箱获取用户
	 * @param username
	 * @return
	 */
	public User getUserUsingEmail(@Param("email")String email);
	public User getUserUsingUser_id(@Param("user_id") long user_id);
	/**
	 * 更新用户提交答案后的运行数据
	 * @param user_id
	 * @param submitted
	 * @param solved
	 * @param score
	 * @return
	 */
	public int updateUserMessage(@Param("user_id")long user_id,
			@Param("submitted")int submitted,
			@Param("solved")int solved,
			@Param("score") int score);
	
	/**
	 * 获得排序后的用户列表
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<User> getUsers(@Param("start") int start,
			@Param("limit") int limit );
	
	
	
	/**
	 * 获取指定用户的排名
	 * @param user_id
	 * @return　返回指定　指定用户的排名
	 */
	public long getRankUsingUserId(@Param("user_id") long user_id);
	
	
	
	
	/**
	 * 获得用户数量
	 * @return
	 */
	public long getNumsofUsers();
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
}
