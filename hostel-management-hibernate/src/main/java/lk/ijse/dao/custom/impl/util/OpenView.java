package lk.ijse.dao.custom.impl.util;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class OpenView {

    public static void openView (String view , AnchorPane pane, String splash)  {

        Stage stage = (Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(OpenView.class.getResource("/view/splashForm.fxml"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.setTitle(view);
        stage.centerOnScreen();

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished((e) -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(OpenView.class.getResource("/view/" +view+".fxml"))));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setTitle(view);
            stage.centerOnScreen();
            fadeOut.play();
        });
    }

    public static void openView (String view , AnchorPane pane){
        Stage stage = (Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(OpenView.class.getResource("/view/" +view+".fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(view);
        stage.centerOnScreen();
    }

    public static void openView (String view ){
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(OpenView.class.getResource("/view/" +view+".fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(view);
        stage.centerOnScreen();
        stage.show();
    }

}
