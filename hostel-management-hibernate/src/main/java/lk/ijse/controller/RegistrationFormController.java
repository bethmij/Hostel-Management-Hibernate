package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.StudentDTO;

import java.util.Date;

public class RegistrationFormController {
    public AnchorPane registerPane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public AnchorPane menuPane;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTel1;
    public TextField txtTel2;
    public TextField txtEmail;
    public DatePicker dob;
    public RadioButton rdMale;
    public RadioButton rdFemale;
    public RadioButton rdOther;
    public JFXButton btnSave;
    public TextField txtNIC;


    public void btnSaveOnAction(ActionEvent actionEvent) {

        String gender = "";

        if(rdMale.isPressed())
            gender = "Male";
        else if(rdFemale.isPressed())
            gender = "Female";
        else if(rdOther.isPressed())
            gender = "Other";

        StudentDTO studentDTO = new StudentDTO(txtNIC.getText(),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtTel1.getText()),
                                    Integer.parseInt(txtTel2.getText()),txtEmail.getText(), dob.getValue(),gender);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtNIC.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel1.clear();
        txtTel2.clear();
        txtEmail.clear();
        dob.setValue(null);
        rdMale.setSelected(false);
        rdFemale.setSelected(false);
        rdFemale.setSelected(false);
    }

    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",registerPane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",registerPane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",registerPane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",registerPane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",registerPane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",registerPane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",registerPane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",registerPane);
    }

    public void maleOnAction(ActionEvent actionEvent) {
    }

    public void femaleOnAction(ActionEvent actionEvent) {
    }

    public void otherOnAction(ActionEvent actionEvent) {
    }
}
