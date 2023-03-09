package com.zowiac.service;

import org.apache.commons.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.util.ByteArrayDataSource;


@Component
public class EmailService {
    private final static int port = 465;
    private final static  String hostName = "smtp.strato.de";
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String body) throws Exception {
        Email email = new SimpleEmail();
        email.setHostName(hostName);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator("info@zowiac.eu", "A4yimdjYk2ySX9q"));
        email.setSSLOnConnect(true);
        email.setFrom("info@zowiac.eu");
        email.setSubject(subject);
        email.setMsg(body);
        email.addTo(to);
        email.send();
    }


    //send mail with attachment
    public void sendMail(String to, String subject, String body, byte[] pdfAsBytes) throws Exception {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(hostName);
        email.setSmtpPort(port);
        email.setAuthenticator(getAuthenticator());
        email.setSSLOnConnect(true);
        email.setFrom("info@zowiac.eu");
        email.setSubject(subject);
        email.setMsg(body);
        email.addTo(to);
        email.attach(new ByteArrayDataSource(pdfAsBytes, "application/pdf"), "Rechnung.pdf", "application/pdf");
        email.send();
    }


    private Authenticator getAuthenticator(){
        return new DefaultAuthenticator("info@zowiac.eu", "A4yimdjYk2ySX9q");
    }

}

