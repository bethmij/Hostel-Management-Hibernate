package lk.ijse.entity.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReserveProjection {
    private String reserveID;
    private String studentID;
    private String name;
    private String roomID;
    private String roomType;
    private String status;
    private String keyMoney;
    private int qty;

}
