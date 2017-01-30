package top.yenvhang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.mapper.CommentMapper;
import top.yenvhang.mapper.MessageMapper;
import top.yenvhang.model.Comment;
import top.yenvhang.model.Message;
import top.yenvhang.model.MultipleComment;

@Service
public class MessageService {
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	CommentMapper commentMapper;
	public List<Message> getConvensationList(long start,int offset,long userId){
		return messageMapper.getConvensationList(start, offset, userId);
	}
	
	public List<Message> getConvensationDetail(long start,int offset,long convensationId){
		return messageMapper.getConvensationDetail(start, offset, convensationId);
	}
	
	public List<Comment>getNewComments(long toId){
		return commentMapper.getNewComments(toId);
		
	}
	public List<MultipleComment>getMultipleComment(long commentId){
		return commentMapper.getMultipleComment(commentId);
		
	}

	public void readComment(Comment comment ) {
		commentMapper.updateComment(comment);
		
	}
}
