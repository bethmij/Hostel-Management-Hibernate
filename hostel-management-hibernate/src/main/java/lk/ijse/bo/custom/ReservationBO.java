package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    List<String> getRoomID();

    List<String> getStudentID();

    RoomDTO getRoombyID(String roomID);

    int getUsedRoom(String roomID);

    String getStuName(String stuID);

    String getReserveID();

    boolean reserveRoom(ReservationDTO reservationDTO);
}
