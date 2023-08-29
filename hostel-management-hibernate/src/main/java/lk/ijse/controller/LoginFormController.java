package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.entity.User;

public class LoginFormController {
    public AnchorPane logPane;
    public TextField txtName;
    public PasswordField txtPassword;
    public static User user;
    LoginBO loginBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.LOGIN);

    public void logInOnAction(ActionEvent actionEvent) {
        user = loginBO.getUser(txtName.getText());
        OpenView.openView("dashboardForm",logPane);
    }

    public void signUpOnAction(MouseEvent mouseEvent) {
        OpenView.openView("signUpform");
    }
}
