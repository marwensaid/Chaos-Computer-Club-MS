package com.chaos.services;

import com.chaos.model.EmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by marwen on 21/12/15.
 */

@Component
public class EmailService implements ApplicationEventPublisherAware,ApplicationListener<EmailEvent>{

    private ApplicationEventPublisher publisher;

    @Value("${support.email}")
    private String supportEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onApplicationEvent(EmailEvent event) {
        this.send(event.getSubject(), event.getContent());
    }

    private void send(String subject, String content) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(supportEmail);
        mailMessage.setReplyTo(supportEmail);
        mailMessage.setFrom(supportEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e)
        {
            e.printStackTrace();
        }

    }

    public void sendEmail(String subject, String content) {
        EmailEvent event = new EmailEvent(this, subject, content);
        publisher.publishEvent(event);
    }


}
