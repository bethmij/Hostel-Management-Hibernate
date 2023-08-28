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
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.tm.ReserveTM;
import lk.ijse.entity.projection.ReserveProjection;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public ComboBox cbSelect;
    PaymentBO paymentBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.PAYMENT);
    ObservableList<ReserveTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        setSelections();
    }




    public void btnSearchOnAction(ActionEvent actionEvent) {
        tbl.getItems().clear();
        List<ReserveProjection> reserve = null;
        ReserveProjection reserveProjection =  null;

        if(cbSelect.getValue().equals("Reservation ID")) {
            reserveProjection = paymentBO.getReservebyReserveID(txtName.getText());
            getTable(reserveProjection);
        }else if (cbSelect.getValue().equals("Student ID"))
            reserve = paymentBO.getReservebyStudentID(txtName.getText());
        else if (cbSelect.getValue().equals("Room ID"))
            reserve = paymentBO.getReservebyRoomID(txtName.getText());

        for (ReserveProjection list : reserve) {
            getTable(list);
        }

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
            getTable(list);
        }
    }

    private void getTable(ReserveProjection list) {
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

    private void setSelections() {
        List<String> selection = new ArrayList<>();
        selection.add("Reservation ID");
        selection.add("Student ID");
        selection.add("Room ID");
        ObservableList<String> dataList = FXCollections.observableArrayList(selection);
        cbSelect.setItems(dataList);
    }

    private void setPaymentBtnOnAction(Button payButton) {
    }

    private void setDeleteBtnOnAction(Button deleteButton) {
        deleteButton.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                ReserveProjection reserve = paymentBO.getReservebyReserveID(String.valueOf(colReserveID.getCellData(tbl.getSelectionModel().getSelectedIndex())));

                RoomDTO room = new RoomDTO(reserve.getRoomID());
                StudentDTO student = new StudentDTO(reserve.getStudentID());
                ReservationDTO reservationDTO = new ReservationDTO(reserve.getReserveID(),room,student, LocalDateTime.now(),reserve.getStatus());

                boolean isDeleted = paymentBO.deleteReservation(reservationDTO);
                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation Details Deleted!").show();
                    setTable();
                }else
                    new Alert(Alert.AlertType.ERROR, "Reservation Details Delete Failed!").show();
            }
        });
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

    public void unpaidOnAction(ActionEvent actionEvent) {
        cbOther.setSelected(false);
        cbPaid.setSelected(false);
        tbl.getItems().clear();
        List<ReserveProjection> reserve = paymentBO.getReservebyPay("Unpaid");
        for (ReserveProjection list : reserve) {
            getTable(list);
        }
    }

    public void halfOnAction(ActionEvent actionEvent) {
        cbNonPaid.setSelected(false);
        cbPaid.setSelected(false);
        tbl.getItems().clear();
        List<ReserveProjection> reserve = paymentBO.getReservebyHalfPay();
        for (ReserveProjection list : reserve) {
            getTable(list);
        }
    }

    public void payNowOnAction(ActionEvent actionEvent) {
        cbOther.setSelected(false);
        cbNonPaid.setSelected(false);
        tbl.getItems().clear();
        List<ReserveProjection> reserve = paymentBO.getReservebyPay("Paid");
        for (ReserveProjection list : reserve) {
            getTable(list);
        }
    }

    public void cbSelectOnAction(ActionEvent actionEvent) {
        txtName.clear();

        if(cbSelect.getValue().equals("Reservation ID")){
            txtName.setPromptText("Reservation ID");
        }else if(cbSelect.getValue().equals("Student ID")){
            txtName.setPromptText("Student ID");
        }else if(cbSelect.getValue().equals("Room ID")) {
            txtName.setPromptText("Room ID");
        }
    }
}


