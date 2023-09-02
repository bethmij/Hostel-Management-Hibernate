package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.PaymentFormController.reserveProjection;
import static lk.ijse.controller.PaymentFormController.reservedProjection;

public class PayFormController implements Initializable {
    public TextField txtEAmount;
    public Label lblReserve;
    public Label lblStuID;
    public Label lblStuName;
    public Label lblRoomID;
    public Label lblRoomType;
    public Label lblTotal;
    public Label lblRemain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblReserve.setText(reservedProjection.getReserveID() );
        lblStuID.setText(reservedProjection.getStudentID());
        lblStuName.setText(reservedProjection.getName());
        lblRoomID.setText(reservedProjection.getRoomID());
        lblRoomType.setText(reserveProjection.getRoomType());
        lblTotal.setText(reserveProjection.getKeyMoney());
        lblRemain.setText(calcRemaining(reserveProjection.getStatus(), reserveProjection.getKeyMoney()));
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
    }

    private String calcRemaining(String status, String keyMoney) {

        if(status.equals("Paid")) {
            return "Rs. 0.00";
        }else if(status.equals("Unpaid")) {
            return "Rs. "+keyMoney;
        }else if (status.contains("Half")){
            String numericPart = status.replaceAll("\\D+", "");
            return ("Rs. "+(Double.valueOf(keyMoney)-Double.valueOf(numericPart)));
        }else {return "";}
    }


}
