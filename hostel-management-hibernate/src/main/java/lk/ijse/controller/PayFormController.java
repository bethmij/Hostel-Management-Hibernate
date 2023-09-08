package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PayBO;

import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.PaymentFormController.projection;
import static lk.ijse.dao.custom.impl.util.SetValidation.txtKeyOnRelease;
import static lk.ijse.dao.custom.impl.util.SetValidation.txtKeyOnType;

public class PayFormController implements Initializable {
    public TextField txtEAmount;
    public Label lblReserve;
    public Label lblStuID;
    public Label lblStuName;
    public Label lblRoomID;
    public Label lblRoomType;
    public Label lblTotal;
    public Label lblRemain;
    public Label lblMoney;
    PayBO payBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.PAY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPayForm();
    }

    private void setPayForm() {
        lblReserve.setText(projection.getReserveID() );
        lblStuID.setText(projection.getStudentID());
        lblStuName.setText(projection.getName());
        lblRoomID.setText(projection.getRoomID());
        lblRoomType.setText(projection.getRoomType());
        lblTotal.setText("Rs. "+projection.getKeyMoney());
        lblRemain.setText(calcRemaining(projection.getStatus(), projection.getKeyMoney()));
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        if(!txtEAmount.getText().isEmpty() && !txtEAmount.getText().equals("0") ) {
            String amount = txtEAmount.getText();
            String status = projection.getStatus();
            String[] remain = lblRemain.getText().split("Rs\\. ");

            if (amount.equals(remain[1])) {
                status = "Paid";
            } else if (amount.compareTo(remain[1]) > 0) {
                int preAmount = Integer.parseInt(projection.getStatus().replaceAll("\\D+", ""));
                String[] newAmount = amount.split("\\.");
                status = "Half Paid:" + (preAmount+Integer.parseInt(newAmount[0]));
            } else if (amount.compareTo(remain[1]) < 0) {
                new Alert(Alert.AlertType.ERROR, "Please check the amount again! ").show();
            }
            boolean isUpdated = payBO.updateStatus(status,projection.getReserveID());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated Successfully!").show();
            } else
                new Alert(Alert.AlertType.ERROR, "Payment Update Failed!").show();
        }else
            new Alert(Alert.AlertType.ERROR, "Please check the amount again! ").show();
    }

    private String calcRemaining(String status, String keyMoney) {

        if(status.equals("Paid")) {
            return "Rs. 0.00";
        }else if(status.equals("Unpaid")) {
            return "Rs. "+keyMoney;
        }else if (status.contains("Half")){
            String numericPart = status.replaceAll("\\D+", "");
            return ("Rs. "+(Double.parseDouble(keyMoney)-Double.valueOf(numericPart)));
        }else {return "";}
    }


    public void txtAmountOnRelease(KeyEvent keyEvent) {
        txtKeyOnRelease(txtEAmount,lblMoney);
    }

    public void txtAmountOnType(KeyEvent keyEvent) {
        txtKeyOnType(txtEAmount,lblMoney);
    }
}
