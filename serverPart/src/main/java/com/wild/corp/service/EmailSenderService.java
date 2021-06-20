package com.wild.corp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailSenderService {
    public static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    @Autowired
    private JavaMailSender emailSender;


    @Async
    public void sendEmail(SimpleMailMessage email) {


        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("alod.section.fete@gmail.com");
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(),true);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.debug("sendEmail getFrom "+email.getFrom());
        logger.debug("sendEmail getFrom "+email.getTo());
        logger.debug("sendEmail getFrom "+email.getSubject());
        logger.debug("sendEmail getText "+email.getText());

        try {
            emailSender.send(message);
        } catch (MailException e) {
            logger.debug("send mail " + e);
        }
    }
}