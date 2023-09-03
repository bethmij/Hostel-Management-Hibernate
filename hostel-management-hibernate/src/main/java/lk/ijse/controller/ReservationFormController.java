package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Room;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static lk.ijse.controller.PaymentFormController.reserveProjection;
import static lk.ijse.controller.StudentManageFormController.studentDTO;
import static lk.ijse.dao.custom.impl.util.SetHeader.setHeader;

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
    public ToggleGroup toggleGroup;
    public JFXButton btnSave;
    public Label lblMoney1;
    ReservationBO reservationBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.RESERVE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payGroup.setVisible(false);
        setRoomID();
        setStudentID();
        setReserveID();
        lblDate.setText(String.valueOf(LocalDate.now()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                lblTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        toggleGroup = new ToggleGroup();

        rdPayNow.setToggleGroup(toggleGroup);
        rdPayHalfNow.setToggleGroup(toggleGroup);
        edPayLater.setToggleGroup(toggleGroup);

        if(reserveProjection!=null){
            setReserveForm();
        }

        setHeader(lblDate,lblTime,circleUser,lblUser);
    }

    private void setReserveForm() {
        lblResID.setText(reserveProjection.getReserveID());
        cmbRoomID.setValue(reserveProjection.getRoomID());
        lblRoomType.setText(reserveProjection.getRoomType());
        cmbStuID.setValue(reserveProjection.getStudentID());
        lblName.setText(reserveProjection.getName());
        lblMoney.setText(reserveProjection.getKeyMoney());
        btnSave.setText("Update");


        int usedRooms = reservationBO.getUsedRoom(reserveProjection.getRoomID());
        lblAvailable.setText(String.valueOf(reserveProjection.getQty()-Integer.valueOf(usedRooms)));

        if(reserveProjection.getStatus().equals("Paid")) {
            rdPayNow.setSelected(true);
        }else if(reserveProjection.getStatus().equals("Unpaid")) {
            edPayLater.setSelected(true);
        }else if (reserveProjection.getStatus().contains("Half")){
            rdPayHalfNow.setSelected(true);
            payGroup.setVisible(true);
            txtPay.setText(reserveProjection.getStatus().replaceAll("\\D+", ""));
        }
    }

    private void setReserveID() {
        String reserveID = reservationBO.getReserveID();
        String numericPart = reserveID.replaceAll("\\D+", "");

        if(numericPart.isEmpty())
            lblResID.setText("R001");
        else
            lblResID.setText("R00"+(Integer.valueOf(numericPart)+1));
    }

    private void setStudentID() {
        List<String> studentList = reservationBO.getStudentID();
        ObservableList<String> dataList = FXCollections.observableArrayList();

        for (String ids : studentList) {
            dataList.add(ids);
        }

        cmbStuID.setItems(dataList);
    }

    private void setRoomID() {
        List<String> roomList = reservationBO.getRoomID();
        ObservableList<String> dataList = FXCollections.observableArrayList();

        if(roomList!=null) {
            for (String ids : roomList) {
                dataList.add(ids);
            }
            cmbRoomID.setItems(dataList);
        }


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

        if(cmbRoomID.isPressed() && cmbStuID.isPressed() || rdPayNow.isSelected() ||
                rdPayHalfNow.isSelected() && !txtPay.getText().isEmpty() || edPayLater.isSelected() ) {
            if (btnSave.getText().equals("Reserve")) {
                String status = "";

                if (rdPayNow.isSelected())
                    status = "Paid";
                else if (edPayLater.isSelected())
                    status = "Unpaid";
                else if (rdPayHalfNow.isSelected())
                    status = "Half Paid:" + txtPay.getText();

                RoomDTO room = new RoomDTO(String.valueOf(cmbRoomID.getValue()));
                StudentDTO student = new StudentDTO(String.valueOf(cmbStuID.getValue()));
                ReservationDTO reservationDTO = new ReservationDTO(lblResID.getText(), room, student, LocalDateTime.now(), status);

               // boolean isSaved = reservationBO.reserveRoom(reservationDTO);

               /* if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved Successfully!").show();
                    getClear();
                    setReserveID();
                } else
                    new Alert(Alert.AlertType.ERROR, "Room Reserve Failed!").show();*/

            } else if (btnSave.getText().equals("Update")) {
                String status = "";

                if (rdPayNow.isSelected())
                    status = "Paid";
                else if (edPayLater.isSelected())
                    status = "Unpaid";
                else if (rdPayHalfNow.isSelected())
                    status = "Half Paid:" + txtPay.getText();

                RoomDTO room = new RoomDTO(String.valueOf(cmbRoomID.getValue()));
                StudentDTO student = new StudentDTO(String.valueOf(cmbStuID.getValue()));
                ReservationDTO reservationDTO = new ReservationDTO(lblResID.getText(), room, student, LocalDateTime.now(), status);

                boolean isUpdated = reservationBO.updateRoom(reservationDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Reservation Updated  Successfully!").show();
                    getClear();
                    setReserveID();
                } else
                    new Alert(Alert.AlertType.ERROR, "Room Reservation Update Failed!").show();

            }
       }else
            new Alert(Alert.AlertType.ERROR, "Please fill up the compulsory fields * ").show();
    }

    private void getClear() {
        setReserveID();
        cmbRoomID.setValue("");
        lblRoomType.setText("");
        cmbStuID.setValue("");
        lblName.setText("");
        lblAvailable.setText("");
        lblMoney.setText("");
        toggleGroup.selectToggle(null);
        payGroup.setVisible(false);
    }



    public void cbRoomOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = reservationBO.getRoombyID(String.valueOf(cmbRoomID.getValue()));
        int usedRooms = reservationBO.getUsedRoom(String.valueOf(cmbRoomID.getValue()));
        lblRoomType.setText(roomDTO.getType());
        lblMoney.setText(roomDTO.getKeyMoney());
        lblAvailable.setText(String.valueOf(roomDTO.getQty()-usedRooms));
    }

    public void cbStuOnAction(ActionEvent actionEvent) {
        String name = reservationBO.getStuName(String.valueOf(cmbStuID.getValue()));
        lblName.setText(name);
    }

    public void rdHalfOnAction(ActionEvent actionEvent) {
        if(rdPayHalfNow.isSelected())
            payGroup.setVisible(true);
        else
            payGroup.setVisible(false);
    }

    public void txtAmountOnRelease(KeyEvent keyEvent) {
        if (txtPay.getText().matches("^(([0-9.]?)*)+$")) {
            txtPay.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblMoney.setText("");
        }
    }

    public void txtAmountOnType(KeyEvent keyEvent) {
        if (!txtPay.getText().matches("^(([0-9.]?)*)+$")) {
            txtPay.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            lblMoney.setText("Only numeric values!");
        }
    }
}
