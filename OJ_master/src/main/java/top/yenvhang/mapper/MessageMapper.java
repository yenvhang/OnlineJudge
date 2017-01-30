package top.yenvhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import top.yenvhang.model.Message;

public interface MessageMapper {
	static final String TABLE="oj_message";
	static final String INSERT_FILEDS ="content,fromId,toId,convensationId";
	static final String SELECT_FILEDS ="messageId,createTime,hasRead,"+INSERT_FILEDS;
	/**
	 * 插入对话消息
	 * @param fromId
	 * @param toId
	 * @param content
	 * @param convensationId
	 */
	@Insert({"insert into ",TABLE,"("+INSERT_FILEDS+")values(#{content},#{fromId},#{toId},#{convensationId})"})
	public int insertMessage(@Param("fromId")long fromId,@Param("toId")long toId,
			@Param("content")String content,@Param("convensationId") long convensationId);
	/**
	 * 查询获取对话列表
	 * @param start
	 * @param offset
	 * @param userId
	 * @return
	 */
	@Select({"select any_value(content) as content,"
			+ "any_value(fromId) as fromId,"
			+ "any_value(toId) as toId,"
			+ "any_value(createTime) as createTime,"
			+ "convensationId "
			+ "from"
			+ "(SELECT content,fromId,toId,createTime,convensationId FROM oj_message "
			+ "where fromId=#{userId} or toId=#{userId} "
			+ "order by createTime desc) temp "
			+ "group by convensationId order by createTime desc limit #{start},#{offset}"})
	public List<Message> getConvensationList(@Param("start")  long start,@Param("offset")long offset,
			@Param("userId") long userId);
	/**
	 * 查询获取获取对话的详细内容
	 * @param start
	 * @param offset
	 * @param convensationId
	 * @return
	 */
	@Select({"select ",SELECT_FILEDS,"from",TABLE,"where convensationId=${convensationId} "
			+ "order by createTime desc"})
	public List<Message> getConvensationDetail(@Param("start")  long start,@Param("offset")long offset,
			@Param("convensationId")long convensationId);
}
