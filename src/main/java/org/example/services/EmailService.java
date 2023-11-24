package org.example.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String password){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("knpk.adrian02@gmail.com");
        message.setTo(to);
        message.setSubject("Your Password");
        message.setText("Your password is: " + password);
        mailSender.send(message);
    }
}
