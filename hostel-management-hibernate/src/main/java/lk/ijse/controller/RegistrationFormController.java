package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.StudentDTO;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static lk.ijse.controller.StudentManageFormController.studentDTO;
import static lk.ijse.dao.custom.impl.util.SetHeader.setHeader;

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
        txtName.setText(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        txtTel1.setText(String.valueOf(studentDTO.getTel1()));
        txtTel2.setText(String.valueOf(studentDTO.getTel1()));
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

        if(btnSave.getText().equals("Save")) {
            String gender = "";

            if (rdMale.isSelected())
                gender = "Male";
            else if (rdFemale.isSelected())
                gender = "Female";
            else if (rdOther.isSelected())
                gender = "Other";

            StudentDTO studentDTO = new StudentDTO(txtNIC.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtTel1.getText()),
                    Integer.parseInt(txtTel2.getText()), txtEmail.getText(), dob.getValue(), gender);
            boolean isSaved = registerBO.saveStudent(studentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Student Successfully!").show();
                getClear();
            }else
                new Alert(Alert.AlertType.ERROR, "Save Student Failed!").show();

        }else if(btnSave.getText().equals("Update")){
            String gender = "";

            if (rdMale.isSelected())
                gender = "Male";
            else if (rdFemale.isSelected())
                gender = "Female";
            else if (rdOther.isSelected())
                gender = "Other";

            StudentDTO studentDTO = new StudentDTO(txtNIC.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtTel1.getText()),
                    Integer.parseInt(txtTel2.getText()), txtEmail.getText(), dob.getValue(), gender);
            boolean isUpdated = registerBO.updateStudent(studentDTO);

            if (isUpdated)
                new Alert(Alert.AlertType.CONFIRMATION, " Student Updated Successfully!").show();
            else
                new Alert(Alert.AlertType.ERROR, " Student Update Failed!").show();
        }
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



}
