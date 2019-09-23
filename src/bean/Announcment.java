package bean;

import java.util.Date;

/**
 *公告实体类
 */
public class Announcment {

	/**
	 * 一条的公告主键
	 */
	private Integer announcementId;
	
	
	/**
	 * 公告标题
	 */
	private String title;
	
	/**
	 * 公告内容
	 */
	private String context;
	
	
	
	/**
	 * 创建该公告的管理员id
	 */
	private Integer createrId;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 是否显示,0 不显示，1 显示
	 */
	private Integer isActive;

	public Integer getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Integer announcementId) {
		this.announcementId = announcementId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
