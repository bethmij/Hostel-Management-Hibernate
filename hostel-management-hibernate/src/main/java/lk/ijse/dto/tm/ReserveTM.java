package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String remaining;
    private Button payment;
    private Button delete;

}
