package lk.ijse.dao.custom.impl.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static lk.ijse.controller.LoginFormController.user;

public class SetHeader {

    public static void setHeader(Label date, Label time, Circle profile, Label userName){
        date.setText(String.valueOf(LocalDate.now()));

        setTime(time);

        setImage(profile);

        userName.setText(user.getUserName());

    }

    private static void setImage(Circle profile) {
        String path = new String(user.getImage());
        InputStream in = null;
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
        profile.setFill(new ImagePattern(image));
    }

    private static void setTime(Label time) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
