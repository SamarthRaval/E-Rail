//Author:Varun
package com.erail.sendMail;

import com.erail.Factory.IServiceFactory;
import com.erail.Factory.ServiceFatory;
import com.erail.controller.BookingController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.util.Properties;

@Component
public class SendMail {

    private static final Logger logger = LogManager.getLogger(SendMail.class);

    private static SendMail instance;
    private IServiceFactory iServiceFactory;

    SendMail() {
        iServiceFactory = new ServiceFatory();
    }

    public static SendMail getInstance() {
        if (instance == null) {
            instance = new SendMail();
        }
        return instance;
    }


    public void sendMail(String to, String subject, String messageText) {
        try {
            logger.info( " sending mail to "+ to + "  subject : " + subject);
            FileReader fr = new FileReader("src/main/resources/application.properties");
            Properties property = new Properties();
            property.load(fr);
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.starttls.required", property.getProperty("spring.mail.properties.mail.smtp.starttls.required"));
            properties.setProperty("mail.smtp.starttls.enabled", property.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
            properties.setProperty("mail.smtp.host", property.getProperty("spring.mail.host"));
            properties.setProperty("mail.smtp.port", property.getProperty("spring.mail.port"));
            properties.put("mail.smtp.auth", property.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));

            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(iServiceFactory.createEmailConfigService().getEmailConfig().get(0).get("email").toString(),
                            iServiceFactory.createEmailConfigService().getEmailConfig().get(0).get("password").toString());
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(iServiceFactory.createEmailConfigService().getEmailConfig().get(0).get("email").toString()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            message.setContent(messageText, "text/html");
            Transport.send(message);
            logger.info( " mail successfully sent ");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
