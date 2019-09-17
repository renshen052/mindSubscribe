package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 翻页工具
 */
public class Pager {
	
	private int pageNo = 1;//目标页号
	
	private int pageCount = 5;//每页记录数
	
	private int totalCount = 0;//总记录数
	
	private int totalPageNo = 1;//总页号
	
	private boolean hasPrev = false;//是否有上一页/首页
	
	private boolean hasNext = false;//是否有下一页/尾页
	
	private String url;
	
	public Pager(HttpServletRequest request){
		
		//接收目标页号
		String pageNo = request.getParameter("pageNo");
		
		if(Util.isNotEmpty(pageNo)){
			this.setPageNo(Integer.parseInt(pageNo));
		}
		
		/*
		 * 是否在上一页/首页
		 */
		
		if(this.pageNo > 1){
			
			this.hasPrev = true;
		}
		
		//取当前请求地址
		this.url = request.getRequestURI() + "?1=1";
		
		/*
		 * 取请求当中所有的参数，并且拼接到url的后面
		 */
		Enumeration<String> parameterNames = request.getParameterNames();
		
		while (parameterNames.hasMoreElements()) {
			
			String name =  parameterNames.nextElement();
			String value = request.getParameter(name);
			
			//如果参数是有值的
			if(Util.isNotEmpty(value) && !"1".equals(name) && !"pageNo".equals(name)){
				
				try {
					value = URLEncoder.encode(value,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
						
				this.url += "&" + name + "=" +  value;
			}
		}
		
		request.setAttribute("pager", this);
		
		
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		//计算总页号
		this.totalPageNo = this.totalCount / this.pageCount;
		
		//如果还有剩余
		if(this.totalCount % this.pageCount > 0){
			
			//再来一页用来盛装剩余的数据,页号+1
			this.totalPageNo++;
		}
		
		/*
		 * 是否在下一页/尾页 
		 */
		if(this.pageNo < this.totalPageNo){
			
			this.hasNext = true;
		}
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}

	public boolean getHasPrev() {
		return hasPrev;
	}

	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	public boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
