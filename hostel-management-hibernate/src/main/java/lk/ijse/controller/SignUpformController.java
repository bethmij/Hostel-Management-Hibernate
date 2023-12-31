package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.LoginFormController.user;
import static lk.ijse.controller.PaymentFormController.reserveProjection;
import static lk.ijse.dao.custom.impl.util.SetValidation.*;


public class SignUpformController implements Initializable {
    public TextField txtName;
    public PasswordField txtPass;
    public PasswordField txtReenter;
    public TextField txtID;
    public TextField txtEmail;
    public Circle circleUser1;
    public TextField txtUserName;
    public JFXButton btnSave;
    public TextField txtPassVisible;
    public TextField txtPassVisible1;
    public Label lblEmail;
    public Label lblName;
    public TextField txtReEnterVisible;
    InputStream in = null;
    String path;
    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(user!=null){
            setSignUpForm();
        }
    }

    private void setSignUpForm() {
        txtID.setText(user.getUserID());
        txtID.setDisable(true);
        txtName.setText(user.getName());
        txtUserName.setText(user.getUserName());
        txtPass.setDisable(true);
        txtReenter.setDisable(true);
        txtEmail.setText(user.getEmail());
        btnSave.setText("Update");
        setImage();
    }

    private void setImage() {

        path = new String(user.getImage());
        try {
            if(path==null) {
                in = new FileInputStream("D:\\IJSE\\Working Projects\\Hostel Management\\hostel-management-hibernate\\src\\main\\resources\\assests\\icons8-user-100 (1).png");

            }else{
                in = new FileInputStream(path);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image image = new Image(in);
        circleUser1.setFill(new ImagePattern(image));
    }

    public void signUpOnAction(ActionEvent actionEvent) {

        if(btnSave.getText().equals("SIGN UP")) {
            byte[] imagePath = path.getBytes();
            String hashed = BCrypt.hashpw(txtReenter.getText(), BCrypt.gensalt());
            UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(), txtUserName.getText(), hashed , txtEmail.getText(), imagePath);
            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Signed Up Successfully!").show();
            } else
                new Alert(Alert.AlertType.ERROR, "Sign Up Failed!").show();

        }else if(btnSave.getText().equals("Update")) {
            byte[] imagePath = path.getBytes();

            UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(), txtUserName.getText(), user.getPassword(), txtEmail.getText(), imagePath);
            boolean isUpdated = userBO.updateUser(userDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully!").show();
            } else
                new Alert(Alert.AlertType.ERROR, "Update Failed!").show();
        }


    }

    public void picOnAction(ActionEvent actionEvent) {

        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        path = file.getPath();
        actionEvent.consume();
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        Image image = new Image(in);
        circleUser1.setFill(new ImagePattern(image));

    }

    public void reEnterOnAction(MouseEvent mouseEvent) {
        if(txtPass.isVisible()) {
            txtPass.setVisible(false);
            txtPassVisible.setVisible(true);
            txtPassVisible.setText(txtPass.getText());
            txtPassVisible.setEditable(false);
        }else{
            txtPass.setVisible(true);
            txtPassVisible.setVisible(false);
        }
        if(txtReenter.isVisible()) {
            txtReenter.setVisible(false);
            txtReEnterVisible.setVisible(true);
            txtReEnterVisible.setText(txtReenter.getText());
            txtReEnterVisible.setEditable(false);
        }else{
            txtReenter.setVisible(true);
            txtReEnterVisible.setVisible(false);
        }
    }

    public void txtEmailOnRelease(KeyEvent keyEvent) {
        txtEmailOnKeyRelease(txtEmail,lblEmail);

    }

    public void txtEmailOnType(KeyEvent keyEvent) {
        txtEmailOnKeyType(txtEmail,lblEmail);
    }

    public void txtNameOnRelease(KeyEvent keyEvent) {
        txtNameOnKeyRelease(txtName,lblName);
    }

    public void txtNameOnType(KeyEvent keyEvent) {
        txtNameOnKeyType(txtName,lblName);
    }
}
