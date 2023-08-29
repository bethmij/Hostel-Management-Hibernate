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
    String path;

    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    public void signUpOnAction(ActionEvent actionEvent) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        //Query query = session.createQuery("SELECT u.image FROM User u WHERE u.userID = :id");
       /* User user = session.get(User.class, "1");
        System.out.println(user);*/
        //String paths = (String) query.uniqueResult();
        //System.out.println("byte"+blobData);
       /* path = paths;
        System.out.println("string"+paths);
        //nputStream in = new ByteArrayInputStream((byte[]) o);
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
       /* System.out.println("input"+in);
            Image image = new Image(in);
        System.out.println("image"+image);
            circleUser1.setFill(new ImagePattern(image));*/


        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        try {
            byte[] imageData = IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       // byte[] imagePath = path.getBytes();

        UserDTO userDTO = new UserDTO(txtID.getText(), txtName.getText(), txtReenter.getText(),txtEmail.getText(),imageData);
        boolean isSaved = userBO.saveUser(userDTO);


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

    }
}
