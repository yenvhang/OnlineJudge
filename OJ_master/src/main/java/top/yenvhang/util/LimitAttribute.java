package top.yenvhang.util;

public class LimitAttribute {
	public LimitAttribute(int page, int pageSize) {
		this.page=page;
		this.pageSize=pageSize;
	}
	
	public int page;
	public int pageSize;
	public int startIndex;
}
