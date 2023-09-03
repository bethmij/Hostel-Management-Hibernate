package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.entity.User;

import java.util.List;

public class LoginFormController {
    public AnchorPane logPane;
    public TextField txtName;
    public PasswordField txtPassword;
    public static User user;
    public TextField txtPassVisible;
    LoginBO loginBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.LOGIN);

    public void logInOnAction(ActionEvent actionEvent) {

        List<String> userNameLists = loginBO.getUserNameList();

        for ( String userName : userNameLists) {
            if(txtName.getText().equals(userName)){
                String password = loginBO.getPassword(userName);
                if(txtPassword.getText().equals(password)){
                    user = loginBO.getUser(userName);
                    OpenView.openView("dashboardForm",logPane);
                }else
                    new Alert(Alert.AlertType.ERROR, "Password mismatched").show();
            }else
                new Alert(Alert.AlertType.ERROR, "Invalid User Name!").show();
        }

    }

    public void signUpOnAction(MouseEvent mouseEvent) {
        OpenView.openView("signUpform");
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
}
