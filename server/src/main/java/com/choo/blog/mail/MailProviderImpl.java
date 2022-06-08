package com.choo.blog.mail;

import com.choo.blog.util.TextTemplateEngine;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class MailProviderImpl implements MailProvider {
    private final MailProperties mailProperties;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine emailTemplateEngine;
    private final static String TEMPLATE_LOCAL = "US";

    public MailProviderImpl(MailProperties mailProperties, JavaMailSender javaMailSender, SpringTemplateEngine emailTemplateEngine) {
        this.mailProperties = mailProperties;
        this.javaMailSender = javaMailSender;
        this.emailTemplateEngine = emailTemplateEngine;
    }


    @Async
    public void send(MailMessage mailMessage) {
        doSend(mailMessage);
    }


    public void send(List<MailMessage> mailMessages) {
        mailMessages.stream().forEach(mailMessage -> send(mailMessage));

    }

    private void doSend(MailMessage mailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, mailMessage.isMultipart(), "UTF-8");
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(mailMessage.getTo());
            helper.setSubject(mailMessage.getSubject());
            if(mailMessage.hasTemplateName()) {
                IContext context = new Context(LocaleContextHolder.getLocale(), mailMessage.getAttributes());
                mailMessage.setText(emailTemplateEngine.process(mailMessage.getTemplateName(), context));
                mailMessage.setHtml(true);
            }
            if(mailMessage.hasTemplateText()){
                String text = TextTemplateEngine.getInstance().getTemplatefromAttributes(mailMessage.getTemplateText(), mailMessage.getAttributes());
                mailMessage.setText(text);
                mailMessage.setHtml(true);
            }
            helper.setText(mailMessage.getText(), mailMessage.isHtml());

            if (mailMessage.getReplyTo() != null) {
                helper.setReplyTo(mailMessage.getReplyTo());
            }
            if (mailMessage.getCc() != null) {
                helper.setCc(mailMessage.getCc());
            }
            if (mailMessage.getBcc() != null) {
                helper.setBcc(mailMessage.getBcc());
            }
            if (mailMessage.getAttachments() != null) {
                for (File attachment : mailMessage.getAttachments()) {
                    helper.addAttachment(attachment.getName(), attachment);
                }
            }

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // TODO Auto-generated catch
            e.printStackTrace();
        }

    }
}
