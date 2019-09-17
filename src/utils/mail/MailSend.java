package utils.mail;

import org.apache.commons.mail.SimpleEmail;

/**
 * 发送邮件
 * 发送邮件配置步骤（以腾讯邮箱为例，其它邮箱大同小异）：
 * 1.加入commons-email-1.4.jar和mail.jar *
 * 2.打开邮件中的POP3/SMTP服务 *
 * 3.生成授权码(授权码只能用来发送邮件，保护邮箱密码) *
 * 4.修改发件邮箱对应的昵称（可选）
 */
public class MailSend {
	
	public static void sendMail(final Mail mail){
		
		//class x implements Runnable{}
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				send(mail);
				
			}
		});
		
		thread.start();
		
	}
	
	@SuppressWarnings("deprecation")
	private static boolean send(Mail mail){
		
		SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.qq.com");//腾讯smtp协议固定主机名

        email.setAuthentication("1452836647@qq.com","sfdlreahziynbacb"); //发送者邮箱的用户名和鉴权码

        email.setCharset("UTF-8");

        try {

            email.setFrom("1452836647@qq.com");//发件人邮箱

            email.addTo(mail.getTo());//收件人邮箱

            email.setSubject(mail.getTitle());//邮件标题

            //email.setMsg(mail.getContent());//邮件内容
            
            //设置内容为html
            email.setContent(mail.getContent(), SimpleEmail.TEXT_HTML);
            
            email.setSSL(true);//使用ssl(Secure Sockets Layer 安全套接层)
            
            email.send();//发送

            System.out.println("邮件发送成功");

        } catch (Exception e) {
             e.printStackTrace();
            System.out.println("邮件发送失败");
            return false;
        }
        
        return true;
	}
	
	public static void main(String[] args) {
		
		Mail mail = new Mail("邮件测试", "<font color=red>这是一封测试邮件</font><br/> 请点以下链接 <a href=\"http://baidu.com\">去百度</a>","renshen052@126.com");
		
		send(mail);
	} 
}
