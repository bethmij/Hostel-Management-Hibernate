package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.tm.RoomTM;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.dao.custom.impl.util.SetHeader.setHeader;
import static lk.ijse.dao.custom.impl.util.SetValidation.*;

public class RoomFormController implements Initializable {
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
    public Group newIDGroup;
    public Group newTypeGroup;
    public JFXButton btnSave;
    public TextField txtSearch;
    public Label lblQty;
    public Label lblMoney;
    RoomBO roomBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.ROOM);
    ObservableList<RoomTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setIDList();
        setTypeList();
        newIDGroup.setDisable(true);
        newTypeGroup.setDisable(true);
        setCellValueFactory();
        setTable();
        setHeader(lblDate,lblTime,circleUser,lblUser);
    }


    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("typeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMonet.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    private void setTypeList() {
        List<String> typeList = roomBo.getRoomType();
        ObservableList<String> dataList = FXCollections.observableArrayList();

        if(typeList!=null) {
            for (String ids : typeList) {
                dataList.add(ids);
            }
            cmbType.setItems(dataList);
        }

    }

    private void setIDList() {
        List<String> idList = roomBo.getRoomID();
        ObservableList<String> dataList = FXCollections.observableArrayList();
         if(idList!=null) {
            for (String ids : idList) {
                dataList.add(ids);
            }
            cmbID.setItems(dataList);
        }
    }

    private void setTable() {
        tbl.getItems().clear();
        List<RoomDTO> roomList = roomBo.getRoomDetail();

        for (RoomDTO room : roomList) {
            Button deleteButton = new Button("Delete");
            deleteButton.setCursor(Cursor.HAND);
            setDeleteBtnOnAction(deleteButton);

            RoomTM roomTM = new RoomTM(room.getTypeId(),room.getType(),room.getKeyMoney(),room.getQty(), deleteButton);
            obList.add(roomTM);
            tbl.setItems(obList);
        }
    }

    private void setDeleteBtnOnAction(Button deleteButton) {
        deleteButton.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                String roomID = String.valueOf(colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));
                List<String> reservationID = roomBo.getReservebyRoomID(roomID);

                boolean isDeleted = false;

                if (!reservationID.isEmpty()) {
                    Notification(reservationID, roomID);
                    ButtonType yess = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType noo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Optional<ButtonType> results = new Alert(Alert.AlertType.INFORMATION, "This room" +
                            " has a reservation! Do you want to delete the reservation with the room details", yess, noo).showAndWait();
                    if (results.orElse(no) == yess) {
                        isDeleted = roomBo.deleteRoom(roomID);
                    }
                } else
                    isDeleted = roomBo.deleteRoom(roomID);

                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Details Deleted!").show();
                    setTable();
                }else
                    new Alert(Alert.AlertType.ERROR, "Room Details Delete Failed!").show();
            }
        });
    }

    private void Notification(List<String> reservationID, String roomID) {
        String reservations = Arrays.toString(reservationID.toArray()).replace("[", "").replace("]", "");
        Notifications.create()
                .title("WARNING\n")
                //.graphic(new ImageView("assests/586f513b350e3f5a1694ec752ae2a183.jpg"))
                .text("Reservation no: "+reservations+ " is reserved under Room ID: "+roomID+"\n" +
                        "All the reservations will be deleted if you proceed further!"  ).
                darkStyle()
                .hideAfter(Duration.seconds(10))
                .show();
    }

    public void IDOnAction(ActionEvent actionEvent) {
        if(newIDGroup.isDisable())
            newIDGroup.setDisable(false);

        cmbID.setValue(txtID.getText());
        txtID.clear();

    }

    public void typeOnAction(ActionEvent actionEvent) {
        if(newTypeGroup.isDisable())
            newTypeGroup.setDisable(false);

        cmbType.setValue(txtType.getText());
        txtType.clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if(!cmbID.isPressed() && !cmbType.isPressed() && !txtQty.getText().isEmpty() && !txtMoney.getText().isEmpty()) {
            if (btnSave.getText().equals("Save")) {
                RoomDTO roomDTO = new RoomDTO(String.valueOf(cmbID.getValue()), String.valueOf(cmbType.getValue()),
                        txtMoney.getText(), Integer.parseInt(txtQty.getText()));
                boolean isSaved = roomBo.saveRoom(roomDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Detail Saved Successfully!").show();
                    setTable();
                    getClear();
                } else
                    new Alert(Alert.AlertType.ERROR, "Room Detail Save Failed!").show();
            } else if (btnSave.getText().equals("Update")) {
                RoomDTO roomDTO = new RoomDTO(String.valueOf(cmbID.getValue()), String.valueOf(cmbType.getValue()),
                        txtMoney.getText(), Integer.parseInt(txtQty.getText()));
                boolean isUpdated = roomBo.updateRoom(roomDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Room Detail Updated Successfully!").show();
                    setTable();
                    getClear();
                } else
                    new Alert(Alert.AlertType.ERROR, "Room Detail Update Failed!").show();
            }
        }else
            new Alert(Alert.AlertType.ERROR, "Please fill up the compulsory fields * ").show();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        getClear();
    }

    private void getClear() {
        cmbID.setValue("");
        cmbType.setValue("");
        newIDGroup.setDisable(true);
        newTypeGroup.setDisable(true);
        txtID.clear();
        txtType.clear();
        txtMoney.clear();
        txtQty.clear();
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


    public void tblOnAction(MouseEvent mouseEvent) {
        getClear();
        if (mouseEvent.getClickCount() == 2){
            String roomID = String.valueOf(colID.getCellData(tbl.getSelectionModel().getSelectedIndex()));

            if(roomID!=null){
                RoomTM roomTM = (RoomTM) tbl.getSelectionModel().getSelectedItem();
                cmbID.setValue(roomTM.getTypeId());
                cmbType.setValue(roomTM.getType());
                txtMoney.setText(roomTM.getKeyMoney());
                txtQty.setText(String.valueOf(roomTM.getQty()));
                btnSave.setText("Update");
            }
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        tbl.getItems().clear();
        RoomDTO room = roomBo.getRoom(txtSearch.getText());
        Button deleteButton = new Button("Delete");
        deleteButton.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(deleteButton);

        RoomTM roomTM = new RoomTM(room.getTypeId(),room.getType(),room.getKeyMoney(),room.getQty(), deleteButton);
        obList.add(roomTM);
        tbl.setItems(obList);
    }

    public void refreshOnAction(ActionEvent actionEvent) {
        setTable();
    }

    public void txtQtyOnReleased(KeyEvent keyEvent) {
        txtQtyOnRelease(txtQty,lblQty);
    }

    public void txtQtyOnTyped(KeyEvent keyEvent) {
        txtQtyOnType(txtQty,lblQty);
    }

    public void txtKeyOnReleased(KeyEvent keyEvent) {
        txtKeyOnRelease(txtMoney,lblMoney);
    }

    public void txtKeyOnTyped(KeyEvent keyEvent) {
        txtKeyOnType(txtMoney,lblMoney);
    }
}
