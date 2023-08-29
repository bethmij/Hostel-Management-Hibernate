package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SettingBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.UserDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static lk.ijse.controller.LoginFormController.user;
import static lk.ijse.dao.custom.impl.util.SetHeader.setHeader;

public class SettingFormController implements Initializable {
    public AnchorPane settingPane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public AnchorPane menuPane;
    public Circle circleUser1;
    public Label lblUserName;
    public TextField txtUserName;
    public PasswordField txtPass;
    public PasswordField txtReEnter;
    public Label lblName;
    InputStream in ;
    String path ;
    SettingBO settingBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.SETTING);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblName.setText(user.getName());
        lblUserName.setText(user.getUserName());

        setImage();
        setHeader(lblDate,lblTime,circleUser,lblUser);



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

    public void userOnAction(ActionEvent actionEvent) {
        boolean isUpdated = settingBO.updateUserName(txtUserName.getText(), user.getUserName());
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated User Name Successfully!").show();
        } else
            new Alert(Alert.AlertType.ERROR, "Update User Name Failed!").show();
    }

    public void passOnAction(ActionEvent actionEvent) {
        boolean isUpdated = settingBO.updatePassword(txtReEnter.getText(), user.getUserName());
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Password Successfully!").show();
        } else
            new Alert(Alert.AlertType.ERROR, "Update Password Failed!").show();
    }

    public void editOnAction(MouseEvent mouseEvent) {
        OpenView.openView("signUpform");
    }


    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",settingPane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",settingPane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",settingPane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",settingPane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",settingPane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",settingPane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",settingPane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",settingPane);
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

        byte[] imagePath = path.getBytes();
        boolean isPicSaved = settingBO.updatePic(imagePath, user.getUserName());
        if (isPicSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Profile Pic Successfully!").show();
        } else
            new Alert(Alert.AlertType.ERROR, "Update Profile Pic Failed!").show();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to delete?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            boolean isDeleted = settingBO.deleteUser(user);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Account Details Deleted!").show();
                user=null;
                OpenView.openView("loginForm",settingPane);
            }else
                new Alert(Alert.AlertType.ERROR, "User Account Delete Failed!").show();
        }
    }


}
