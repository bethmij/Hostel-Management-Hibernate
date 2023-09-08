package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReserveTM {
    private String reserveID;
    private String studentID;
    private String name;
    private String roomID;
    private String roomType;
    private String status;
    private LocalDate date;
    private String remaining;
    private Button payment;
    private Button delete;

}
