package lk.ijse.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DashboardBO;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.impl.util.OpenView;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Student;
import lk.ijse.entity.projection.ReserveProjection;
import org.hibernate.Session;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;
import static lk.ijse.controller.LoginFormController.user;
import static lk.ijse.dao.custom.impl.util.SendMail.sendAttach;
import static lk.ijse.dao.custom.impl.util.SetHeader.setHeader;

public class DashboardFormController implements Initializable {
    public AnchorPane dashPane;
    public Circle circleUser;
    public Label lblDate;
    public Label lblTime;
    public Label lblUser;
    public Label lblStudent;
    public Label lblRoom;
    public Label lblUsed;
    public Label lblRemain;
    public Label lblNoAcTotal;
    public Label lblNoAcAvailable;
    public Label lblNoAcFoodTotal;
    public Label lblNoAcFoodAvailable;
    public Label lblAcTotal;
    public Label lblAcAvailable;
    public Label lblAcFoodTotal;
    public Label lblAcFoodAvailable;
    public AnchorPane menuPane;
    DashboardBO dashboardBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.DASHBOARD);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuPane.setVisible(false);
        setHeader(lblDate,lblTime,circleUser,lblUser);
        setDashBoard();
        sendEmail();
    }



    public DashboardFormController(){
        Session session = SessionFactoryConfig.getInstance().getSession();

    }

    private void setDashBoard() {
        lblStudent.setText(String.valueOf(dashboardBO.getTotalStudent()));
        lblRoom.setText(String.valueOf(dashboardBO.getTotalRooms()));
        lblUsed.setText(String.valueOf(dashboardBO.getReservedCount()));
        lblRemain.setText(String.valueOf(dashboardBO.getTotalRooms()-dashboardBO.getReservedCount()));
        lblNoAcTotal.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-1324")));
        lblNoAcFoodTotal.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-5467")));
        lblAcTotal.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-7896")));
        lblAcFoodTotal.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-0093")));
        lblNoAcAvailable.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-1324")-dashboardBO.getReservedRoomsSep("RM-1324")));
        lblNoAcFoodAvailable.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-5467")-dashboardBO.getReservedRoomsSep("RM-5467")));
        lblAcAvailable.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-7896")-dashboardBO.getReservedRoomsSep("RM-7896")));
        lblAcFoodAvailable.setText(String.valueOf(dashboardBO.getTotalRoomsSep("RM-0093")-dashboardBO.getReservedRoomsSep("RM-0093")));
    }

    public void dashbordOnAction(MouseEvent mouseEvent) {
        OpenView.openView("dashboardForm",dashPane);
    }

    public void registrationOnAction(MouseEvent mouseEvent) {
        OpenView.openView("registrationForm",dashPane);
    }

    public void manageOnAction(MouseEvent mouseEvent) {
        OpenView.openView("studentManageForm",dashPane);
    }

    public void roomsOnAction(MouseEvent mouseEvent) {
        OpenView.openView("roomForm",dashPane);
    }

    public void reserveOnAction(MouseEvent mouseEvent) {
        OpenView.openView("reservationForm",dashPane);
    }

    public void settingOnAction(MouseEvent mouseEvent) {
        OpenView.openView("settingForm",dashPane);
    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        OpenView.openView("loginForm",dashPane);
    }

    public void menuOpenOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(true);
    }

    public void menuCloseOnAction(MouseEvent mouseEvent) {
        menuPane.setVisible(false);
    }

    public void payOnAction(MouseEvent mouseEvent) {
        OpenView.openView("paymentForm",dashPane);
    }

    public void sendEmail (){
        List<ReserveProjection> reservationList = dashboardBO.getReservation();

        for (ReserveProjection list : reservationList) {
            LocalDate reserveDate = dashboardBO.getReserveDate(list.getReserveID());
            String email = dashboardBO.getEmail(list.getReserveID());
            long dayCount = DAYS.between(LocalDate.now(), reserveDate);
            if(dayCount==7){
                if(!email.isEmpty()){
                    String remain =calcRemaining(list.getStatus(),list.getKeyMoney());
                    LocalDate dueDate = reserveDate.plusDays(7);
                    String message =  "Reservation ID  -  "+list.getReserveID()+"\n" +
                            "Student ID      -  " +
                            "This is to fomally remind you that the payment owed by you on reservation "+list.getReserveID()+"" +
                            "is due in one week."+remain+"was due for payment on"+dueDate+"Kindly pay the amount immediately\n" +
                            "Kindly disregard this notice if payment has already been made. We advise you settle your dues" +
                            "immediately to avoid furthur penalties\n" +
                            "Thank you for your immediate action regarding the matter\n\n" +
                            "Sincerely\nD24 Hostel Administration ";
                    String subject = "D24 Hotel Administration  : Payment Reminder Notice ";
                    String to = user.getEmail();
                    String from = "bethmij@gmail.com";
                    sendAttach(message,subject,to,from);
                }
            }
        }



    }

    private String calcRemaining(String status, String keyMoney) {

        if(status.equals("Paid")) {
            return "";
        }else if(status.equals("Unpaid")) {
            return "Rs. "+keyMoney;
        }else if (status.contains("Half")){
            String numericPart = status.replaceAll("\\D+", "");
            return ("Rs. "+(Double.valueOf(keyMoney)-Double.valueOf(numericPart)));
        }else {return "";}
    }
}
