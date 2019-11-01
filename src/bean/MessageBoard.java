package bean;

import java.util.Date;

/**
 * @author h w j
 * @instruction
 * 留言板  messageBoard ,对应的实体类
 */
public class MessageBoard {

	/**
	 * 留言id
	 */
	private Integer boardId ;
	
	/**
	 * 留言内容
	 */
	private String context ;
	
	/**
	 * 留言创建者Id
	 */
	private Integer createrId ;
	
	/**
	 * 留言时间
	 */
	private Date createTime;
	
	/**
	 * 是否显示
	 */
	private Integer isActive;
	
	/**
	 * 创建留言的来访者
	 * @return
	 */
	private Client client;

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "MessageBoard [boardId=" + boardId + ", context=" + context + ", createrId=" + createrId
				+ ", createTime=" + createTime + ", isActive=" + isActive + ", client=" + client + "]\n";
	}
	
}

