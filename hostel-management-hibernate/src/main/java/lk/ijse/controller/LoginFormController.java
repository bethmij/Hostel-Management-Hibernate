package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dao.custom.impl.util.OpenView;

public class LoginFormController {
    public AnchorPane logPane;

    public void logInOnAction(ActionEvent actionEvent) {
        OpenView.openView("dashboardForm",logPane);
    }

    public void signUpOnAction(MouseEvent mouseEvent) {
    }
}
