package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.LoginFormController.user;
import static lk.ijse.controller.PaymentFormController.reserveProjection;


public class SignUpformController implements Initializable {
    public TextField txtName;
    public PasswordField txtPass;
    public PasswordField txtReenter;
    public TextField txtID;
    public TextField txtEmail;
    public Circle circleUser1;
    public TextField txtUserName;
    public JFXButton btnSave;
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

            UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(), txtUserName.getText(), txtReenter.getText(), txtEmail.getText(), imagePath);
            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Signed Up Successfully!").show();
            } else
                new Alert(Alert.AlertType.ERROR, "Sign Up Failed!").show();

        }else if(btnSave.getText().equals("Update")) {
            byte[] imagePath = path.getBytes();

            UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(), txtUserName.getText(), txtReenter.getText(), txtEmail.getText(), imagePath);
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

    
}
