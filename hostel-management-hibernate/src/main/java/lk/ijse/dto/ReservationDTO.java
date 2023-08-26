package lk.ijse.dto;


import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {

    private String reserveID;
    private Room room;
    private Student student;
    private Date date;
    private String status;
}
