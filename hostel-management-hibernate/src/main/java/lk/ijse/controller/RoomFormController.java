package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.tm.RoomTM;
import lk.ijse.entity.Room;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
                 RoomDTO room = roomBo.getRoom(String.valueOf(colID.getCellData(tbl.getSelectionModel().getSelectedIndex())));

                boolean isDeleted = roomBo.deleteRoom(room);
                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Details Deleted!").show();
                    setTable();
                }else
                    new Alert(Alert.AlertType.ERROR, "Room Details Delete Failed!").show();
            }
        });
    }

    public void IDOnAction(ActionEvent actionEvent) {
        if(newIDGroup.isDisable())
            newIDGroup.setDisable(false);

        cmbID.setValue(txtID.getText());

    }

    public void typeOnAction(ActionEvent actionEvent) {
        if(newTypeGroup.isDisable())
            newTypeGroup.setDisable(false);

        cmbType.setValue(txtType.getText());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = new RoomDTO(String.valueOf(cmbID.getValue()), String.valueOf(cmbType.getValue()),
                                txtMoney.getText(), Integer.parseInt(txtQty.getText()));
        boolean isSaved = roomBo.saveRoom(roomDTO);

        if(isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Room Detail Successfully!").show();
            setTable();
        }else
            new Alert(Alert.AlertType.ERROR,"Save Room Detail Failed!").show();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        cmbID.setValue("");
        cmbType.setValue("");
        newIDGroup.setDisable(true);
        newTypeGroup.setDisable(true);
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


}
