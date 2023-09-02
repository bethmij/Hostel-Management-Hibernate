package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTM {

    private String studentID;
    private String name;
    private String address;
    private int tel1;
    private String tel2;
    private String email;
    private LocalDate dob;
    private String gender;
    private Button button;

}
