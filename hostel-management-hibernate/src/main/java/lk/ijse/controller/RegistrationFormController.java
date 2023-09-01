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
            if(!txtName.getText().isEmpty() && !txtNIC.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtTel1.getText().isEmpty()) {
                String gender = "";

                if (rdMale.isSelected())
                    gender = "Male";
                else if (rdFemale.isSelected())
                    gender = "Female";
                else if (rdOther.isSelected())
                    gender = "Other";

                int tel2 = 0;
                if(!txtTel2.getText().isEmpty()){
                    tel2 = Integer.parseInt(txtTel2.getText());
                }

                StudentDTO studentDTO = new StudentDTO(txtNIC.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtTel1.getText()),
                        tel2, txtEmail.getText(), dob.getValue(), gender);
                boolean isSaved = registerBO.saveStudent(studentDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Student Successfully!").show();
                    getClear();
                } else
                    new Alert(Alert.AlertType.ERROR, "Save Student Failed!").show();

            }else
                new Alert(Alert.AlertType.ERROR, "Please fill up the compulsory fields * ").show();

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


    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        if (!txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle(" -fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            lblName.setText("This filed can not contain numeric values!");
        }
    }

    public void txtNameOnKeyTyped(KeyEvent keyEvent) {
        if (txtName.getText().matches("^[A-Za-z\\s]*$")) {
            txtName.setStyle("-fx-effect: null; -fx-font-size: 16px;");
            lblName.setText("");
        }
    }

    public void txtTel1OnKeyReleased(KeyEvent keyEvent) {
        if (txtTel1.getText().matches("^[0-9]*$")) {
            txtTel1.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblTel1.setText("");
        }
        if (!(txtTel1.getText().length() == 10)) {
            txtTel1.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            lblTel1.setText("Not a valid contact number!");
        }
        if(txtTel1.getText().equals("")){
            txtTel1.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblTel1.setText("");
        }
    }

    public void txtTel1OnKeyTyped(KeyEvent keyEvent) {

        if (!txtTel1.getText().matches("^[0-9]*$")) {
            txtTel1.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            lblTel1.setText("Not a valid contact number!");
        }
        if ((txtTel1.getText().length() == 10)) {
            txtTel1.setStyle("-fx-effect: null; -fx-font-size: 16px;");
            lblTel1.setText(" ");
        }
        if(txtTel1.getText().equals("")){
            txtTel1.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblTel1.setText("");
        }
    }

    public void txtTel2OnKeyReleased(KeyEvent keyEvent) {
        if (!txtTel2.getText().matches("^[0-9]*$")) {
            txtTel2.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            lblTel2.setText("Not a valid contact number!");
        }
        if (!(txtTel2.getText().length() == 10)) {
            txtTel2.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            lblTel2.setText("Not a valid contact number!");
        }
        if(txtTel2.getText().equals("")){
            txtTel2.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblTel2.setText("");
        }
    }

    public void txtTel2OnKeyTyped(KeyEvent keyEvent) {
        if (txtTel2.getText().matches("^[0-9]*$")) {
            txtTel2.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblTel2.setText("");
        }
        if (txtTel2.getText().length() == 10) {
            txtTel2.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblTel2.setText("");
        }
        if(txtTel2.getText().equals("")){
            txtTel2.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblTel2.setText("");
        }
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            txtEmail.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3);; -fx-font-size: 16px;");
            lblEmail.setText("Invalid Email Format!");
        }
        if(txtEmail.getText().equals("")){
            txtEmail.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblEmail.setText("");
        }
    }

    public void txtEmailOnKeyTyped(KeyEvent keyEvent) {
        if (txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            txtEmail.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblEmail.setText("");
        }
        if(txtEmail.getText().equals("")){
            txtEmail.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            lblEmail.setText("");
        }
    }
}
