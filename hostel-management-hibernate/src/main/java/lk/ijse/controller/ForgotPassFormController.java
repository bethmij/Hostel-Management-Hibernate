package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.dto.UserDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import static lk.ijse.controller.LoginFormController.user;

public class ForgotPassFormController {
    public TextField txtVerification;
    public JFXButton btnSave;
    public JFXButton btnVerify;
    public TextField txtVerification1;
    public PasswordField txtPasswordVisible;
    public JFXButton btnSave1;
    public Label lblEmail;

    public void btnVerifyOnAction(ActionEvent actionEvent) {
    }

    public void btnResendOnAction(MouseEvent mouseEvent) {
    }

    public void passOnAction(MouseEvent mouseEvent) {
    }

    public void btnSetPassOnAction(ActionEvent actionEvent) {
    }

    private static void sendAttach(String message, String subject, String to, String from) {

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
                return new PasswordAuthentication("bethmij@gmail.com", "vkxyewuzwrfhtghs");
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

    public void sendEmail (){
        UserDTO userDTO = null;
        try {
            userDTO = passwordBO.searchByUser(user);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        Random random = new Random();
        code = random.nextInt(99999-11111) + 11111;

        String message =  "Employee ID  -  "+userDTO.getEmployee()+"\n" +
                "Username      -  "+userDTO.getUser()+"\n" +
                "Verification Code  -  "+code;
        String subject = "Grama Vista : Email verification";
        String to = userDTO.getEmail();
        String from = "gramavista@gmail.com";
        sendAttach(message,subject,to,from);

    }
}
