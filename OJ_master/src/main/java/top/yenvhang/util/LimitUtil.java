package top.yenvhang.util;

import org.springframework.ui.Model;

public class LimitUtil {
	private int defautPageSize=20;
	/**
	 * 设置默认的ＰageSize大小
	 * @param defautPageSize
	 */
	public void setDefautPageSize(int defautPageSize) {
		this.defautPageSize = defautPageSize;
	}

	/**
	 * @param model　将分页属性填充到Ｍodel中
	 * @param totals 数据总个数
	 * @param page 第几页
	 * @param pageSize　每页大小
	 *
	 * @return 返回分页属性
	 */
	public  LimitAttribute limit(Model model,long totals,int page,int pageSize){
		if (page <= 0) 
			page = 1;
		if (pageSize > 100 || pageSize < 10)
			pageSize = 20;
		LimitAttribute limitAttribute =new LimitAttribute(page,pageSize);
		limitAttribute.startIndex=pageSize*(page-1);
		limitAttribute.pageSize=pageSize;
		model.addAttribute("totals",totals);
		model.addAttribute("page", page);
		model.addAttribute("pageSize",pageSize);
		return limitAttribute;
	}
	/**
	 * 
	 * @param model 将分页属性填充到Ｍodel中
	 * @param totals 数据总个数
	 * @return 返回分页属性
	 */
	public  LimitAttribute limit(Model model,long totals){
		return limit(model, totals, 1, defautPageSize);
	}
	/**
	 * 
	 * @param model 将分页属性填充到Ｍodel中
	 * @param totals 数据总个数
	 * @param page 第几页
	 * @return 返回分页属性
	 */
	public  LimitAttribute limit(Model model,long totals,int page){
		return limit(model, totals, page, defautPageSize);
	}
}

