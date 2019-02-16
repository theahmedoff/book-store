package com.book.store.util;


import com.book.store.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.to}")
    private String to;

    @Value("${spring.mail.subject}")
    private String subject;


    public void sendEmailMessage(Contact contact) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Firstname: "+contact.getFirstName()+"\nLastname: "+contact.getLastName()+"\nEmail: "+contact.getEmail()+"\nWebsite: "+contact.getWebsite()+"\nSubject: "+contact.getSubject()+"\nMessage: "+contact.getMessage());
        emailSender.send(message);

    }
}
