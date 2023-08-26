package lk.ijse.dto;

import lk.ijse.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {

    private String studentID;
    private String name;
    private String address;
    private int tel1;
    private int tel2;
    private String email;
    private LocalDate dob;
    private String gender;

}
