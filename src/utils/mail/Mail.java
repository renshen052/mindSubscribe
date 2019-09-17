package utils.mail;

/**
 * 邮件类
 */
public class Mail {

	String title;//邮件标题
	String content;//邮件内容
	String to;//邮件接收人
	
	
	public Mail() {

	}
	
	public Mail(String title, String content, String to) {
		this.title = title;
		this.content = content;
		this.to = to;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
}
