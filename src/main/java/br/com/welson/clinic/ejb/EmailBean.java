package br.com.welson.clinic.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailBean {

    @Resource(name = "java:jboss/mail/Gmail")
    private Session session;

    public void send(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
