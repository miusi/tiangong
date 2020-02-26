package com.kaizhuo.component.message.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.message.sender
 * @description:
 * @author: miaochen
 * @create: 2020-02-25 19:35
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Component
@Slf4j
public class MailSender {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     *
     * @param from    发件人
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleEmail(String from, String[] to, String subject, String content) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发件人
        message.setFrom(from);
        //设置收件人
        message.setTo(to);
        //设置主题
        message.setSubject(subject);
        //设置内容
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送html邮件
     *
     * @param from    发件人
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlEmail(String from, String[] to, String subject, String content) throws MessagingException {
        //创建一个mime消息
        MimeMessage message = mailSender.createMimeMessage();
        //true表示需要创建multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     *
     * @param from               发件人
     * @param to                 收件人
     * @param subject            主题
     * @param content            内容
     * @param attachmentFilename 附件名
     * @param inputStreamSource  附件内容
     * @throws MessagingException
     */
    public void sendAttachmentsEmail(String from, String[] to, String subject, String content, String attachmentFilename, InputStreamSource inputStreamSource) throws MessagingException {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        // true表示这个邮件是有附件的
        helper.setText(content, true);
        //添加附件
        helper.addAttachment(attachmentFilename, inputStreamSource);
        mailSender.send(message);
    }
}
