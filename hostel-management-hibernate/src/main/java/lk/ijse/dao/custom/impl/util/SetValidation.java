package lk.ijse.dao.custom.impl.util;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javax.swing.*;


public class SetValidation {

    public static void txtNameOnKeyRelease(TextField textField, Label label) {
        if (!textField.getText().matches("^[A-Za-z\\s]*$")) {
            textField.setStyle(" -fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            label.setText("This filed can not contain numeric values!");
        }
    }

    public static void txtNameOnKeyType(TextField textField, Label label) {
        if (textField.getText().matches("^[A-Za-z\\s]*$")) {
            textField.setStyle("-fx-effect: null; -fx-font-size: 16px;");
            label.setText("");
        }
    }

    public static void txtTelOnKeyRelease(TextField textField, Label label) {
        if (textField.getText().matches("^[0-9]*$")) {
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
        if (!(textField.getText().length() == 10)) {
            textField.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            label.setText("Not a valid contact number!");
        }
        if(textField.getText().equals("")){
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
    }

    public static void txtTelOnKeyType(TextField textField, Label label) {

        if (!textField.getText().matches("^[0-9]*$")) {
            textField.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            label.setText("Not a valid contact number!");
        }
        if ((textField.getText().length() == 10)) {
            textField.setStyle("-fx-effect: null; -fx-font-size: 16px;");
            label.setText(" ");
        }
        if(textField.getText().equals("")){
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
    }


    public static void txtEmailOnKeyRelease(TextField textField, Label label) {
        if (!textField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            textField.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3);; -fx-font-size: 16px;");
            label.setText("Invalid Email Format!");
        }
        if(textField.getText().equals("")){
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
    }

    public static void txtEmailOnKeyType(TextField textField, Label label) {
        if (textField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
        if(textField.getText().equals("")){
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
    }

    public static void txtQtyOnRelease(TextField textField, Label label) {
        if (textField.getText().matches("^[0-9]*$")) {
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
    }

    public static void txtQtyOnType(TextField textField, Label label) {
        if (!textField.getText().matches("^[0-9]*$")) {
            textField.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            label.setText("Should only contains numeric values!");
        }
    }

    public static void txtKeyOnRelease(TextField textField, Label label) {
        if (textField.getText().matches("^(([0-9.]?)*)+$")) {
            textField.setStyle("-fx-effect:  null; -fx-font-size: 16px;");
            label.setText("");
        }
    }

    public static void txtKeyOnType(TextField textField, Label label) {
        if (!textField.getText().matches("^(([0-9.]?)*)+$")) {
            textField.setStyle("-fx-effect: innershadow(gaussian, #ac0a2d, 20, 0, 3, 3); -fx-font-size: 16px;");
            label.setText("Should only contains numeric values!");
        }
    }
}
