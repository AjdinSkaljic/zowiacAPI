package com.zowiac.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String body) throws Exception {
        Email email = new SimpleEmail();
        email.setHostName("smtp.strato.de");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("info@zowiac.eu", "A4yimdjYk2ySX9q"));
        email.setSSLOnConnect(true);
        email.setFrom("info@zowiac.eu");
        email.setSubject(subject);
        email.setMsg(body);
        email.addTo(to);
        email.send();
    }

}

