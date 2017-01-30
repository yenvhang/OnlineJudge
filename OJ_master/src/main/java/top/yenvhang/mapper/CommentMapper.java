package top.yenvhang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.yenvhang.model.Comment;
import top.yenvhang.model.MultipleComment;

public interface CommentMapper {
	public List<Comment> getCommentsByEntityId(@Param("entity_id") long problemId,
			@Param("entity_type") int entity_type);
	
	public void createComment(Comment comment);
	public long getCountNewMessages(long from_id,long to_id);
//	public List<MultipleComment> getNewComments(@Param("to_id")long to_id);
	public List<Comment> getNewComments(@Param("to_id")long to_id);

	public List<MultipleComment> getMultipleComment(long commentId);
	public void updateComment(Comment comment);
} 
