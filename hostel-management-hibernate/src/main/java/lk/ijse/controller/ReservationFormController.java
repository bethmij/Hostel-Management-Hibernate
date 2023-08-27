package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.impl.util.OpenView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {
    public AnchorPane reservePane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public AnchorPane menuPane;
    public TextField txtPay;
    public ComboBox cmbRoomID;
    public ComboBox cmbStuID;
    public Label lblRoomType;
    public Label lblName;
    public Label lblAvailable;
    public Label lblMoney;
    public RadioButton rdPayNow;
    public RadioButton edPayLater;
    public RadioButton rdPayHalfNow;
    public Label lblResID;
    public Group payGroup;
    ObservableList<String> dataList = FXCollections.observableArrayList();
    ReservationBO reservationBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.RESERVE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payGroup.setVisible(false);
        setRoomID();
    }

    private void setRoomID() {
        reservationBO.getRoomID();
    }

    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",reservePane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",reservePane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",reservePane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",reservePane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",reservePane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",reservePane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",reservePane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",reservePane);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }



    public void payLaterOnAction(ActionEvent actionEvent) {
        if( !edPayLater.isSelected()){
            rdPayHalfNow.setDisable(false);
            rdPayNow.setDisable(false);
        }else{
            rdPayHalfNow.setDisable(true);
            rdPayNow.setDisable(true);
        }
    }

    public void payHalfOnAction(ActionEvent actionEvent) {
        if( !rdPayHalfNow.isSelected()){
            rdPayNow.setDisable(false);
            edPayLater.setDisable(false);
            payGroup.setVisible(false);
        }else{
            rdPayNow.setDisable(true);
            edPayLater.setDisable(true);
            payGroup.setVisible(true);
        }
    }


    public void paymentOnAction(ActionEvent actionEvent) {
        if( !rdPayNow.isSelected()){
            rdPayHalfNow.setDisable(false);
            edPayLater.setDisable(false);
        }else{
            rdPayHalfNow.setDisable(true);
            edPayLater.setDisable(true);
        }
    }
}
