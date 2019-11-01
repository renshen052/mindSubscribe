package bean;

import java.util.Date;

/**
 * @author h w j
 * @instruction
 * 消息表 msessage ,对应的实体类
 */
public class Message {

	/**
	 * 消息的id
	 */
	private Integer messageId;
	
	/**
	 * 消息的发送者身份
	 */
	private String sender;
	
	/**
	 * 消息发送者的id
	 */
	private Integer senderId;
	
	/**
	 * 消息发送者的名字
	 */
	private String senderName;
	
	/**
	 * 消息接受者的身份
	 */
	private String receiver;
	
	/**
	 * 消息接受者的id
	 */
	private Integer receiverId;
	
	/**
	 * 消息接受者的名字
	 */
	private String receiverName;
	
	/**
	 * 发送时间
	 */
	private Date sendTime;
	
	/**
	 * 消息是否已经被读
	 */
	private Integer isRead;
	
	/**
	 * 消息内容
	 */
	private String context;
	
	/**
	 * 身份是管理员
	 */
	public final static String ADMIN = "admin";
	
	/**
	 * 身份是咨询师
	 */
	public final static String DOCTOR = "doctor";
	
	/**
	 * 身份是来访者
	 */
	public final static String CLIENT = "client";
	

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", sender=" + sender + ", senderId=" + senderId + ", senderName="
				+ senderName + ", receiver=" + receiver + ", receiverId=" + receiverId + ", receiverName="
				+ receiverName + ", sendTime=" + sendTime + ", isRead=" + isRead + ", context=" + context + "]";
	}
	
	
}
