package com.itbaizhan.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public final class MailUtils {
    @Value("${mail.user}")
    private String USER; // 发件人邮箱
    @Value("${mail.password}")
    private String PASSWORD; // 客户端授权码
    @Value("${mail.host}")
    private String HOST; // SMTP主机
    @Value("${mail.port}")
    private String PORT; // 端口

    public boolean sendMail(String to, String text, String title) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", HOST);
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.starttls.enable", "true"); // 587端口启用STARTTLS

            // 授权信息
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER, PASSWORD);
                }
            };

            // 创建会话
            Session mailSession = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(mailSession);

            // 设置发件人
            message.setFrom(new InternetAddress(USER));
            // 设置收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 标题
            message.setSubject(title);
            // 内容（支持HTML）
            message.setContent(text, "text/html;charset=UTF-8");

            // 发送
            Transport.send(message);
            return true;
        } catch (AuthenticationFailedException e) {
            System.err.println("认证失败：请检查邮箱账号或客户端授权码是否正确");
            e.printStackTrace();
        } catch (MessagingException e) {
            System.err.println("发送失败：可能是SMTP主机、端口配置错误，或收件人邮箱无效");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("发送失败：未知错误");
            e.printStackTrace();
        }
        return false;
    }
}