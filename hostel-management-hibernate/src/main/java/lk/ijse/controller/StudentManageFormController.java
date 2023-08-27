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
import lk.ijse.bo.custom.ManageBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.tm.RoomTM;
import lk.ijse.dto.tm.StudentTM;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentManageFormController implements Initializable {
    public AnchorPane managePane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public AnchorPane menuPane;
    public TextField txtName;
    public TableView tbl;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTel1;
    public TableColumn colTel2;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colAction;
    public TableColumn colGmail;
    ManageBO manageBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.MANAGE);
    ObservableList<StudentTM> obList = FXCollections.observableArrayList();
    static StudentDTO studentDTO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel1.setCellValueFactory(new PropertyValueFactory<>("tel1"));
        colTel2.setCellValueFactory(new PropertyValueFactory<>("tel2"));
        colGmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        tbl.getItems().clear();
        StudentDTO studentDTO = manageBO.getStudent(txtName.getText());
        Button deleteButton = new Button("Delete");
        deleteButton.setCursor(Cursor.HAND);
        setDeleteBtnOnAction(deleteButton);

        StudentTM studentTM = new StudentTM(studentDTO.getStudentID(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getTel1(),
                studentDTO.getTel2(),studentDTO.getEmail(),studentDTO.getDob(),studentDTO.getGender(),deleteButton);
        obList.add(studentTM);
        tbl.setItems(obList);
    }

    private void setTable() {
        tbl.getItems().clear();
        List<StudentDTO> studentList = manageBO.getStudentl();

        for (StudentDTO studentDTO : studentList) {
            Button deleteButton = new Button("Delete");
            deleteButton.setCursor(Cursor.HAND);
            setDeleteBtnOnAction(deleteButton);

            StudentTM studentTM = new StudentTM(studentDTO.getStudentID(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getTel1(),
                            studentDTO.getTel2(),studentDTO.getEmail(),studentDTO.getDob(),studentDTO.getGender(),deleteButton);
            obList.add(studentTM);
            tbl.setItems(obList);
        }
    }

    private void setDeleteBtnOnAction(Button deleteButton) {
        deleteButton.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                StudentDTO studentDTO = manageBO.getStudent(String.valueOf(colID.getCellData(tbl.getSelectionModel().getSelectedIndex())));

                boolean isDeleted = manageBO.deleteStudent(studentDTO);
                if(isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Details Deleted!").show();
                    setTable();
                }else
                    new Alert(Alert.AlertType.ERROR, "Room Details Delete Failed!").show();
            }
        });
    }

    public void btnGetAllOnAction(ActionEvent actionEvent) {
        setTable();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        studentDTO = manageBO.getStudent(txtName.getText());
        OpenView.openView("registrationForm");
    }

    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",managePane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",managePane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",managePane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",managePane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",managePane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",managePane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",managePane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",managePane);
    }


}
