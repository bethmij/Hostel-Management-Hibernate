package lk.ijse.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.impl.util.OpenView;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    public AnchorPane dashPane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public Label lblStudent;
    public Label lblRoom;
    public Label lblUsed;
    public Label lblRemain;
    public Label lblNoAcTotal;
    public Label lblNoAcAvailble;
    public Label lblNoAcFoodTotal;
    public Label lblNoAcFoodAvailble;
    public Label lblAcTotal;
    public Label lblAcAvailble;
    public Label lblAcFoodTotal;
    public Label lblAcFoodAvailble;
    public AnchorPane menuPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPane.setVisible(false);
    }

    public DashboardFormController(){
    }

    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",dashPane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",dashPane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",dashPane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",dashPane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",dashPane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",dashPane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",dashPane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",dashPane);
    }


}
