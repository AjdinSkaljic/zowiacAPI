package com.zowiac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String body)  {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);
        email.setFrom("info@zowiac.eu");
        mailSender.send(email);
    }


    public void sendMail(String to, String subject, String body, String attachmentName, byte[] pdfAsBytes) throws Exception {
        javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();

        org.springframework.mail.javamail.MimeMessageHelper helper =
                new org.springframework.mail.javamail.MimeMessageHelper(mimeMessage, true);

        helper.setFrom("info@zowiac.eu");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        org.springframework.core.io.ByteArrayResource attachment =
                new org.springframework.core.io.ByteArrayResource(pdfAsBytes);

        helper.addAttachment(attachmentName + ".pdf", attachment, "application/pdf");

        // E-Mail senden
        mailSender.send(mimeMessage);
    }


}

