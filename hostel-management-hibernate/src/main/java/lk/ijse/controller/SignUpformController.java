package lk.ijse.controller;

import javafx.event.ActionEvent;
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

import static org.apache.commons.io.IOUtils.toByteArray;

public class SignUpformController {
    public TextField txtName;
    public PasswordField txtPass;
    public PasswordField txtReenter;
    public TextField txtID;
    public TextField txtEmail;
    public Circle circleUser1;
    InputStream in = null;

    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    public void signUpOnAction(ActionEvent actionEvent) {

//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Query<byte[]> query = session.createQuery("SELECT u.image FROM User u WHERE u.userID = :id", byte[].class);
//        query.setParameter("id","U034");
//        byte[] blobData = (byte[]) query.uniqueResult();
//        System.out.println("blob"+blobData);
//        InputStream in = new ByteArrayInputStream(blobData);
//        System.out.println("input"+in);
//            Image image = new Image(in);
//        System.out.println("image"+image);
//            circleUser1.setFill(new ImagePattern(image));

        byte[] imageData = null;
        try {
            imageData = toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }



        UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(), txtReenter.getText(),txtEmail.getText(),imageData);
        boolean isSaved = userBO.saveUser(userDTO);

//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Query query = session.createQuery("SELECT u FROM User u WHERE u.userID = :userid", User.class);
//        query.setParameter("userid","U034");
//        User user = (User) query.getSingleResult();
//        System.out.println(user);

    }

    public void picOnAction(ActionEvent actionEvent) {

        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        actionEvent.consume();
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        System.out.println(in);
//
//        if(in==null) {
//            try {
//                in = new FileInputStream("D:\\IJSE\\Working Projects\\Hostel Management\\hostel-management-hibernate\\src\\main\\resources\\assests\\icons8-user-100 (1).png");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
        Image image = new Image(in);
        System.out.println(image);
        circleUser1.setFill(new ImagePattern(image));

//        byte[] imageData = null;
//
//        try {
//            imageData = toByteArray(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("imageData"+imageData);
//
//        in = new ByteArrayInputStream(imageData);
//        System.out.println(in);
//
//        image = new Image(in);
//        System.out.println(image);


    }
}
