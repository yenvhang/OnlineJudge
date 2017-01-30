package top.yenvhang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.yenvhang.mapper.MessageMapper;
import util.StringUtil;

@Service
public class DiscussService {
	@Autowired
	MessageMapper messageMapper;
	public int sendMessage(long fromId,long toId,String content){
		long convensationId =Integer.valueOf(fromId<toId?(fromId+""+toId):(toId+""+fromId));
		return messageMapper.insertMessage(fromId, toId, content, convensationId);
	}
}
