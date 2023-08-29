package lk.ijse.controller;

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

import static lk.ijse.controller.PaymentFormController.reserveProjection;


public class SignUpformController implements Initializable {
    public TextField txtName;
    public PasswordField txtPass;
    public PasswordField txtReenter;
    public TextField txtID;
    public TextField txtEmail;
    public Circle circleUser1;
    public TextField txtUserName;
    InputStream in = null;
    String path;
    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(reserveProjection!=null){
            setSignUpForm();
        }
    }

    private void setSignUpForm() {
    }

    public void signUpOnAction(ActionEvent actionEvent) {
    
        byte[] imagePath = path.getBytes();

        UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(),txtUserName.getText(), txtReenter.getText(),txtEmail.getText(),imagePath);
        boolean isSaved = userBO.saveUser(userDTO);

        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Signed Up Successfully!").show();
        } else
            new Alert(Alert.AlertType.ERROR, "Sign Up Failed!").show();


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
