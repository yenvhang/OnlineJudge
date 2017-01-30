package top.yenvhang.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MultipleComment {
	
	private String subject;
	private long comment_id;
	private String content;
	private long entity_id;
	private int entity_type;
	private User from;
	private User to;
	private Date createTime;
	
	
	

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public long getComment_id() {
		return comment_id;
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(long entity_id) {
		this.entity_id = entity_id;
	}
	public int getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(int entity_type) {
		this.entity_type = entity_type;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
