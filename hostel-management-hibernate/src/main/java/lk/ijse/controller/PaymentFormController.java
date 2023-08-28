package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.tm.ReserveTM;
import lk.ijse.dto.tm.RoomTM;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

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
    public TableColumn colPayment;
    PaymentBO paymentBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.PAYMENT);
    ObservableList<ReserveTM> obList = FXCollections.observableArrayList();

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        setTable();
    }

    public void setTable(){
        tbl.getItems().clear();
        List<ReserveProjection> reserveList = paymentBO.getReserveDetail();



        for (ReserveProjection list : reserveList) {
            Button deleteButton = new Button("Delete");
            deleteButton.setCursor(Cursor.HAND);
            setDeleteBtnOnAction(deleteButton);

            Button payButton = new Button("Payment");
            payButton.setCursor(Cursor.HAND);
            setPaymentBtnOnAction(payButton);

            String remaining = calcRemaining(list.getStatus(), list.getKeyMoney());

            ReserveTM reserveTM = new ReserveTM(list.getReserveID(), list.getStudentID(), list.getName(),list.getRoomID(),
                                    list.getRoomType(),list.getStatus(),remaining,payButton,deleteButton);
            obList.add(reserveTM);
            tbl.setItems(obList);
        }

    }

    private String calcRemaining(String status, String keyMoney) {
        switch (status){
            case "Paid" :
                return "";
            case "Unpaid" :
                return keyMoney;
            case "Half Paid" :
                return String.valueOf((Double.valueOf(keyMoney)/2));
            default:
                return "";
        }
    }

    private void setPaymentBtnOnAction(Button payButton) {
    }

    private void setDeleteBtnOnAction(Button deleteButton) {
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


