package com.lms.training.function;

import com.lms.training.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;



@Configuration
public class EmployeeFunction {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeFunction.class);

    @Bean
    public Consumer<String> sendEmailToUsers(EmailService emailService){
        return userEmail -> {
            logger.info("EMPLOYEE MAIL ------------- {} ", userEmail);
            if(userEmail != null){
            logger.info("About to update communication status");
            emailService.sendEmailToUsers(userEmail);

            }
        };
    }
}
