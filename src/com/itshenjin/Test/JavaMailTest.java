package com.itshenjin.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * User: sj
 * Date: 2018/12/17
 * Time: 9:30
 * Description:java发送邮箱
 */
public class JavaMailTest {
		// 发件人的邮箱和密码
		public static String emailAccount = "xxxxxxxxx";
		// 发件人邮箱密码（有的是授权码）
		public static String emailPassword = "xxxxxxxxxx";
		//  发件人邮箱服务地址
		public static String emailSMTPHost = "smtp.qq.com";
		//  收件人邮箱
		public static String receiveMailAccount = "xxxxxxxx";
		/**
		 *  创建一封邮件(发件人、收件人、邮件内容)
		 * @param session
		 * @param sendMail
		 * @param receiveMail
		 * @param html
		 * @return
		 * @throws MessagingException
		 * @throws IOException 
		 * cc:抄送、 Bcc:密送、 To:发送
		 */
		public static  MimeMessage creatMimeMessage(Session session,String sendMail,String receiveMail,String html) throws MessagingException, IOException {
			// 1、创建一封邮件对象
			MimeMessage message = new MimeMessage(session);
			// 2、From：发件人
			message.setFrom(new InternetAddress(sendMail, "发件人昵称", "UTF-8"));
			// 3、To:收件人（可以增加多个收件人：抄送或者密送）
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "张三", "UTF-8"));
			// 4、Subject:邮件主题
			message.setSubject("邮箱验证","UTF-8");
			// 5、Content:邮件正文（可以使用Html标签）
			message.setContent("邮箱验证","text/html;charset=UTF-8");
			// 6、设置发送时间
			message.setSentDate(new Date());
			// 7、保存设置
			message.saveChanges();
			// 8、将该邮件保存在本地
			OutputStream out = new FileOutputStream("D://MyEmail" + UUID.randomUUID().toString() + ".eml");
			message.writeTo(out);
			out.flush();
			out.close();
			return message;
		}
	//  javaMail邮件发送
	public static void main(String[] args) {
		try {
			// 1、创建参数配置，用于连接邮箱服务器的参数配置
			Properties props = new  Properties();
			//  开启debug调试
			props.setProperty("mail.debug", "true");
			// 发送服务器需要身份验证
			props.setProperty("mail.smtp.auth", "true");
			// 设置右键服务器的主机名
			props.setProperty("mail.host", emailSMTPHost);
			// 发送邮件协议名称
			props.setProperty("mail.transport.protocol", "smtp");
			// 2、根据配置创建会话对象，用于和邮件服务器交互
			Session session = Session.getInstance(props);
			//  设置debug，可以查看详细的发送log
			session.setDebug(true);
			//  3、创建一封邮件
			MimeMessage message = creatMimeMessage(session, emailAccount, receiveMailAccount, " ");
			//  4、根据session获取邮件传输对象
			Transport transport = session.getTransport();
			//  5、使用邮箱账号和密码连接邮箱服务器emailAccount必须与message中的发件人邮箱一致，否则报错
			transport.connect(emailAccount,emailPassword);
			//  6、发送邮件,发送所有收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			//  7、关闭连接
			transport.close();
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
