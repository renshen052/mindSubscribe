package utils.mail;

import org.apache.commons.mail.SimpleEmail;

import utils.ConfigProperties;

/**
 * @author h w j
 * @instruction
 * 发送邮件工具
 */
public class MailSend {
	
	public static void sendMail(final Mail mail){
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				send(mail);
				
			}
		});
		
		thread.start();
		
	}
	
	@SuppressWarnings("deprecation")
	public static boolean send(Mail mail){
		
		System.out.println(mail.getContent() + "邮件内容");
		
		SimpleEmail email = new SimpleEmail();

        email.setHostName(ConfigProperties.getHostName());

        email.setAuthentication(ConfigProperties.getAuthenticationEmail(),ConfigProperties.getAuthenticationPwd()); //发送者邮箱的用户名和鉴权码

        email.setCharset("UTF-8");

        try {

            email.setFrom(ConfigProperties.getAuthenticationEmail());//发件人邮箱

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
}
