package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.dao.custom.impl.util.OpenView;

public class RoomFormController {
    public AnchorPane roomPane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public AnchorPane menuPane;
    public TextField txtID;
    public TextField txtType;
    public ComboBox cmbID;
    public ComboBox cmbType;
    public TextField txtQty;
    public TextField txtMoney;
    public TableView tbl;
    public TableColumn colID;
    public TableColumn colType;
    public TableColumn colQty;
    public TableColumn colMonet;
    public TableColumn colAction;



    public void IDOnAction(ActionEvent actionEvent) {
    }

    public void typeOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }


    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",roomPane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",roomPane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",roomPane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",roomPane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",roomPane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",roomPane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",roomPane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",roomPane);
    }
}
