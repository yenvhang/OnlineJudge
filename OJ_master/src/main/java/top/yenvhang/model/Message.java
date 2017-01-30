package top.yenvhang.model;

import java.util.Date;

public class Message {
	private int messageId;
	private long fromId;
	private long toId;
	private String content;
	private Date createTime;
	private boolean hasRead;
	private long convensationId;
	
	public long getConvensationId() {
		return convensationId;
	}
	public void setConvensationId(long convensationId) {
		this.convensationId = convensationId;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isHasRead() {
		return hasRead;
	}
	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}
	public Message(long fromId, long toId, String content) {
		super();
		this.fromId = fromId;
		this.toId = toId;
		this.content = content;
	}
	public Message(){}
	
	
	
}
