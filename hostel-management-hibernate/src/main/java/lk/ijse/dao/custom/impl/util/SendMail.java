package lk.ijse.dao.custom.impl.util;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendMail {
    public static void sendAttach(String message, String subject, String to, String from) {

        String host="smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("d24hostel@gmail.com", "cgkeizaufbezmoul");
            }});

        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try {
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart fileMime = new MimeBodyPart();

            try {
                textMime.setText(message);
                mimeMultipart.addBodyPart(textMime);

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            m.setContent(mimeMultipart);
            Transport.send(m);

        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        System.out.println("Sent success...................");

    }

}
