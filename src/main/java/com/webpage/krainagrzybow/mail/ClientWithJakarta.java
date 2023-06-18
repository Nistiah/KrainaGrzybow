package com.webpage.krainagrzybow.mail;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class ClientWithJakarta {
    private static final String mailFrom = "mailstworzonydozadaniazjavy@gmail.com";
    private String mailTo;
    private static final String host = "smtp.gmail.com";
    private static final String password = "csmrkztjouhftgkj";

    Session session;
    Properties properties = new Properties();


    public void sendMail(String mailAdress, String attachmentFilePath) {
        try {
            mailTo = mailAdress;
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            message.setSubject("Zamówienie");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the invoice attached.");

            BodyPart attachmentBodyPart = new MimeBodyPart();
            FileDataSource dataSource = new FileDataSource(attachmentFilePath);
            attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
            attachmentBodyPart.setFileName(dataSource.getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Address[] recipientAddresses = InternetAddress.parse(mailTo);
            message.setRecipients(Message.RecipientType.TO, recipientAddresses);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, 587, mailFrom, password);
            transport.sendMessage(message, recipientAddresses);
            transport.close();

        } catch (MessagingException mex) {
            System.out.println("Error: " + mex.getMessage());
        }
    }

    public ClientWithJakarta(){
        properties.setProperty("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        session = Session.getDefaultInstance(properties);
    }
}


