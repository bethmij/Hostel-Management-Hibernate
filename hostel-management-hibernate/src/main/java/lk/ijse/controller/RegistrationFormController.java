package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.StudentDTO;

import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.StudentManageFormController.studentDTO;
import static lk.ijse.dao.custom.impl.util.SetHeader.setHeader;
import static lk.ijse.dao.custom.impl.util.SetValidation.*;

public class RegistrationFormController implements Initializable {
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
    public ToggleGroup toggleGroup;
    public Label lblName;
    public Label lblTel1;
    public Label lblTel2;
    public Label lblEmail;
    RegisterBO registerBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.REGISTER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHeader(lblDate,lblTime,circleUser,lblUser);

        if(studentDTO!=null){
            setRegisterForm();
        }

        toggleGroup = new ToggleGroup();
        rdFemale.setToggleGroup(toggleGroup);
        rdMale.setToggleGroup(toggleGroup);
        rdOther.setToggleGroup(toggleGroup);
    }

    private void setRegisterForm() {
        txtNIC.setText(studentDTO.getStudentID());
        txtNIC.setDisable(true);
        txtName.setText(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        txtTel1.setText(String.valueOf(studentDTO.getTel1()));
        txtTel2.setText(String.valueOf(studentDTO.getTel2()));
        txtEmail.setText(studentDTO.getEmail());
        dob.setValue(studentDTO.getDob());
        switch (studentDTO.getGender()){
            case "Male" : rdMale.setSelected(true);
                            break;
            case "Female" : rdFemale.setSelected(true);
                            break;
            case "Other" : rdOther.setSelected(true);
                            break;
         }
        btnSave.setText("Update");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if(!txtName.getText().isEmpty() && !txtNIC.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtTel1.getText().isEmpty()) {

            String gender = "";
            if (rdMale.isSelected())
                gender = "Male";
            else if (rdFemale.isSelected())
                gender = "Female";
            else if (rdOther.isSelected())
                gender = "Other";

            int tel2 = 0;
            if (!txtTel2.getText().isEmpty()) {
                tel2 = Integer.parseInt(txtTel2.getText());
            }

            StudentDTO studentDTO = new StudentDTO(txtNIC.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtTel1.getText()),
                    tel2, txtEmail.getText(), dob.getValue(), gender);

            if (btnSave.getText().equals("Save")) {
                boolean isSaved = registerBO.saveStudent(studentDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Student Successfully!").show();
                    getClear();
                } else
                    new Alert(Alert.AlertType.ERROR, "Save Student Failed!").show();

            } else if (btnSave.getText().equals("Update")) {
                boolean isUpdated = registerBO.updateStudent(studentDTO);

                if (isUpdated)
                    new Alert(Alert.AlertType.CONFIRMATION, " Student Updated Successfully!").show();
                else
                    new Alert(Alert.AlertType.ERROR, " Student Update Failed!").show();
            }
        }else
            new Alert(Alert.AlertType.ERROR, "Please fill up the compulsory fields * ").show();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        getClear();
    }

    private void getClear() {
        txtNIC.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel1.clear();
        txtTel2.clear();
        txtEmail.clear();
        dob.setValue(null);
        toggleGroup.selectToggle(null);
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

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        txtNameOnKeyRelease(txtName,lblName);
    }

    public void txtNameOnKeyTyped(KeyEvent keyEvent) {
        txtNameOnKeyType(txtName,lblName);
    }

    public void txtTel1OnKeyReleased(KeyEvent keyEvent) {
        txtTelOnKeyRelease(txtTel1,lblTel1);
    }

    public void txtTel1OnKeyTyped(KeyEvent keyEvent) {
        txtTelOnKeyType(txtTel1,lblTel1);
    }

    public void txtTel2OnKeyReleased(KeyEvent keyEvent) {
        txtTelOnKeyRelease(txtTel2,lblTel2);
    }

    public void txtTel2OnKeyTyped(KeyEvent keyEvent) {
        txtTelOnKeyType(txtTel2,lblTel2);
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        txtEmailOnKeyRelease(txtEmail,lblEmail);
    }

    public void txtEmailOnKeyTyped(KeyEvent keyEvent) {
        txtEmailOnKeyType(txtEmail,lblEmail);
    }
}
