package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.entity.User;
import org.controlsfx.control.Notifications;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class LoginFormController {
    public AnchorPane logPane;
    public TextField txtName;
    public PasswordField txtPassword;
    public static User user;
    public TextField txtPassVisible;
    public ImageView imgCloseEye;
    public ImageView imgOpenEye;
    LoginBO loginBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.LOGIN);

    public void logInOnAction(ActionEvent actionEvent) {
        if(!txtName.getText().isEmpty() && !txtPassword.getText().isEmpty()) {

            List<String> userNameLists = loginBO.getUserNameList();

            for (String userName : userNameLists) {
                if (txtName.getText().equals(userName)) {
                    String password = loginBO.getPassword(userName);
                    if (BCrypt.checkpw(txtPassword.getText(), password)) {
                        user = loginBO.getUser(userName);
                        OpenView.openView("dashboardForm", logPane,"splash");
                    } else
                        new Alert(Alert.AlertType.ERROR, "Password mismatched").show();
                } else
                    new Alert(Alert.AlertType.ERROR, "Invalid User Name!").show();
            }
        }else
            new Alert(Alert.AlertType.ERROR, "Please fill up all fields!").show();
    }

    public void signUpOnAction(MouseEvent mouseEvent) {
        OpenView.openView("signUpform");
    }

    public void passOnAction(MouseEvent mouseEvent) {
        if(txtPassword.isVisible()) {
            imgCloseEye.setVisible(false);
            imgOpenEye.setVisible(true);
            txtPassword.setVisible(false);
            txtPassVisible.setVisible(true);
            txtPassVisible.setText(txtPassword.getText());
            txtPassVisible.setEditable(false);
        }else{
            imgCloseEye.setVisible(true);
            imgOpenEye.setVisible(false);
            txtPassword.setVisible(true);
            txtPassVisible.setVisible(false);
        }
    }

    public void forgotOnAction(MouseEvent mouseEvent) {
        if(!txtName.getText().isEmpty() ){
            List<String> userNameLists = loginBO.getUserNameList();
            for (String userName : userNameLists) {
                if (txtName.getText().equals(userName)) {
                    user = loginBO.getUser(userName);
                    if (user.getEmail().isEmpty())
                        new Alert(Alert.AlertType.ERROR, "User doesn't have an email, This feature can't proceed further!").show();
                    else
                        Notification();
                        OpenView.openView("forgotPassForm");
                }else
                    new Alert(Alert.AlertType.ERROR, "User Name mismatched!").show();
            }
        }else
            new Alert(Alert.AlertType.ERROR, "Please enter your user Name!").show();
    }

    private void Notification() {
        Notifications.create()
                .title("SENDING MAIL\n")
                .graphic(new ImageView("D:\\IJSE\\Working Projects\\Hostel Management\\hostel-management-hibernate\\src\\main\\resources\\assests\\output-onlinegiftools (10).gif"))
                .text("Sending verification code to your email...."  ).darkStyle()
                .hideAfter(Duration.seconds(3))
                .show();
    }
}
