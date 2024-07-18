package com.lms.training.service.impl;

import com.lms.training.service.EmailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {


    private JavaMailSender emailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public  void sendEmailToUsers(String userEmail){
        String subject = "Queries updates";
        String message = "You got an update in query field,please check your dashboard";
        logger.info("Email didn't? - {}",subject);
        sendEmail(userEmail,subject,message);
        logger.info("Email sent? - {}",subject);
    }
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("aditisingh27022002@gmail.com");
        message.setTo("mahikaakk2@gmail.com");
        message.setSubject(subject);
        message.setText(text);

        try{
            logger.info("just sent? - {}",to);
            emailSender.send(message);
        }
        catch (Exception e){

            System.out.printf(e.getMessage());
        }finally{
            logger.info("Nope sent? - {}",to);
        }

    }

}
