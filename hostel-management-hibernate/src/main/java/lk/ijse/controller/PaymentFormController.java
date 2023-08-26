package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.dao.custom.impl.util.OpenView;

public class PaymentFormController {
    public AnchorPane payPane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public AnchorPane menuPane;
    public TextField txtName;
    public CheckBox cbPaid;
    public CheckBox cbNonPaid;
    public CheckBox cbOther;
    public TableView tbl;
    public TableColumn colReserveID;
    public TableColumn colStuID;
    public TableColumn colName;
    public TableColumn colRoomID;
    public TableColumn colType;
    public TableColumn colStatus;
    public TableColumn colRemain;
    public TableColumn colAction;
    public TableColumn colAction1;

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
    }



    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",payPane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",payPane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",payPane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",payPane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",payPane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",payPane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",payPane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",payPane);
    }
}
