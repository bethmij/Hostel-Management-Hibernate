package lk.ijse.dto;

import lk.ijse.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {

    private String typeId;
    private String type;
    private String keyMoney;
    private int qty;

}
