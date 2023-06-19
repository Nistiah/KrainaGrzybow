package com.webpage.krainagrzybow.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Properties;

@Service
public class ClientWithJakarta {

    public boolean sendMailWithAttachedPDF(String filename, String mailTo) throws MessagingException {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost("smtp.gmail.com");
        emailSender.setPort(587);
        emailSender.setUsername("mailstworzonydozadaniazjavy@gmail.com");
        emailSender.setPassword("csmrkztjouhftgkj");

        Properties props = emailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("Zamówienie");
        helper.setText("W załączniku znajduje się twoja faktura za zamówienie");
        helper.setTo(mailTo);
        helper.addAttachment("mojaFaktura.pdf", new File(filename));
        emailSender.send(message);
        return (true);
    }
}


