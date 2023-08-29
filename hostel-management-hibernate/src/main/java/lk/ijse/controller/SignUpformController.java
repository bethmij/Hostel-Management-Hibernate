package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDTO;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SignUpformController {
    public TextField txtName;
    public PasswordField txtPass;
    public PasswordField txtReenter;
    public TextField txtID;
    public TextField txtEmail;
    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    public void signUpOnAction(ActionEvent actionEvent) {

        InputStream imageStream = getClass().getResourceAsStream("/path/to/your/image.jpg");
        byte[] imageData = null;
        try {
            imageData = IOUtils.toByteArray(imageStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(), txtReenter.getText(),txtEmail.getText(),imageData);
        boolean isSaved = userBO.saveUser(userDTO);
    }
}
