package com.lms.training.service;

public interface EmailService {
    void sendEmailToUsers(String userEmail);
    void sendEmail(String to, String subject, String text);
}
