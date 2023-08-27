package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {

    private String reserveID;
    private RoomDTO room;
    private StudentDTO student;
    private LocalDateTime date;
    private String status;
}
