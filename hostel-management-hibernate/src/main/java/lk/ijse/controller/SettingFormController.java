package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SettingBO;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.dto.UserDTO;

import java.net.URL;
import java.util.ResourceBundle;

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
    SettingBO settingBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.SETTING);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Session session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT u.image FROM User u WHERE u.userID = :id",byte[].class);

        byte[] bytes = (byte[]) query.setParameter("id", "U001").uniqueResult();
        String paths = new String(bytes);

        try {
            in = new FileInputStream(paths);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            Image image = new Image(in);
            circleUser1.setFill(new ImagePattern(image));*/
        //UserDTO userDTO = settingBO.getUser();

    }

    public void userOnAction(ActionEvent actionEvent) {
    }

    public void passOnAction(ActionEvent actionEvent) {
    }

    public void editOnAction(MouseEvent mouseEvent) {
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
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }


}
