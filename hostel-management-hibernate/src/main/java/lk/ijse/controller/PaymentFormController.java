package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
    }



    public void btnSearchOnAction(ActionEvent actionEvent) {
        tbl.getItems().clear();
        ReserveProjection reserve = paymentBO.getReservebyID(txtName.getText());
        System.out.println(reserve);
        /*for (ReserveProjection list : reserveList) {
            Button deleteButton = new Button("Delete");
            deleteButton.setCursor(Cursor.HAND);
            setDeleteBtnOnAction(deleteButton);

            Button payButton = new Button();

            if(!list.getStatus().equals("Paid")) {
                payButton.setText("Pay");
                payButton.setCursor(Cursor.HAND);
                setPaymentBtnOnAction(payButton);
            }else {
                payButton = null;
            }

            String remaining = calcRemaining(list.getStatus(), list.getKeyMoney());
            if(remaining.equals("")){
                remaining = "---";
            }

            String status = "";
            if(list.getStatus().equals("Paid")) {
                status = "Paid";
            }else if(list.getStatus().equals("Unpaid")) {
                status = "Unpaid";
            }else if(list.getStatus().contains("Half")){
                status = "Half Paid";
            }

            ReserveTM reserveTM = new ReserveTM(list.getReserveID(), list.getStudentID(), list.getName(),list.getRoomID(),
                    list.getRoomType(),status,remaining,payButton,deleteButton);
            obList.add(reserveTM);
            tbl.setItems(obList);
        }*/
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

            Button payButton = new Button();

            if(!list.getStatus().equals("Paid")) {
                payButton.setText("Pay");
                payButton.setCursor(Cursor.HAND);
                setPaymentBtnOnAction(payButton);
            }else {
                payButton = null;
            }

            String remaining = calcRemaining(list.getStatus(), list.getKeyMoney());
            if(remaining.equals("")){
                remaining = "---";
            }

            String status = "";
            if(list.getStatus().equals("Paid")) {
                status = "Paid";
            }else if(list.getStatus().equals("Unpaid")) {
                status = "Unpaid";
            }else if(list.getStatus().contains("Half")){
                status = "Half Paid";
            }

            ReserveTM reserveTM = new ReserveTM(list.getReserveID(), list.getStudentID(), list.getName(),list.getRoomID(),
                                    list.getRoomType(),status,remaining,payButton,deleteButton);
            obList.add(reserveTM);
            tbl.setItems(obList);
        }

    }

    private String calcRemaining(String status, String keyMoney) {

        if(status.equals("Paid")) {
            return "";
        }else if(status.equals("Unpaid")) {
            return keyMoney;
        }else if (status.contains("Half")){
            String numericPart = status.replaceAll("\\D+", "");
            return String.valueOf((Double.valueOf(keyMoney)-Double.valueOf(numericPart)));
        }else {return "";}
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

    private void setCellValueFactory() {
        colReserveID.setCellValueFactory(new PropertyValueFactory<>("reserveID"));
        colStuID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("remaining"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }
}


