package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ForgotPassBO;
import org.mindrot.jbcrypt.BCrypt;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static lk.ijse.controller.LoginFormController.loginUser;;
import static lk.ijse.dao.custom.impl.util.SendMail.sendAttach;

public class ForgotPassFormController implements Initializable {
    public TextField txtVerification;
    public JFXButton btnVerify;
    public JFXButton btnSave1;
    public Label lblEmail;
    public PasswordField txtPassword;
    public TextField txtPassVisible;
    int code;
    ForgotPassBO forgotPassBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.FORGOTPASS);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendEmail();
    }

    public void btnVerifyOnAction(ActionEvent actionEvent) {
        if (code==Integer.parseInt(txtVerification.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Verification code confirmed!").show();
            txtPassword.setDisable(false);
            btnSave1.setDisable(false);
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid verification code!").show();
        }
    }

    public void btnResendOnAction(MouseEvent mouseEvent) {
        sendEmail();
    }

    public void passOnAction(MouseEvent mouseEvent) {
        if(txtPassword.isVisible()) {
            txtPassword.setVisible(false);
            txtPassVisible.setVisible(true);
            txtPassVisible.setText(txtPassword.getText());
            txtPassVisible.setEditable(false);
        }else{
            txtPassword.setVisible(true);
            txtPassVisible.setVisible(false);
        }
    }

    public void btnSetPassOnAction(ActionEvent actionEvent) {

            String hashed = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
            boolean isSaved = forgotPassBO.updatePassword(hashed,loginUser.getUserName());
            if (isSaved)
                new Alert(Alert.AlertType.CONFIRMATION,"Password has been reset successfully!").show();
            else
                new Alert(Alert.AlertType.CONFIRMATION,"Password reset failed!").show();
    }



    public void sendEmail (){
        Random random = new Random();
        code = random.nextInt(99999-11111) + 11111;

        String message =  "User ID  -  "+loginUser.getUserID()+"\n" +
                "Username      -  "+loginUser.getUserName()+"\n" +
                "Verification Code  -  "+code;
        String subject = "D24 Hotel Administration  : Email verification";
        String to = loginUser.getEmail();
        String from = "d24hostel@gmail.com";
        sendAttach(message,subject,to,from);

    }


}
