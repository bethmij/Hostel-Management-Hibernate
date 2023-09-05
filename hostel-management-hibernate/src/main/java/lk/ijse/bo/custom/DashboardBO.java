package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.projection.ReserveProjection;

import java.time.LocalDate;
import java.util.List;

public interface DashboardBO extends SuperBO {
    int getTotalStudent();

    int getTotalRooms();

    int getReservedCount();

    int getTotalRoomsSep(String id);

    int getReservedRoomsSep(String s);

    List<ReserveProjection> getReservation();

    LocalDate getReserveDate(String reserveID);

    String getEmail(String reserveID);
}
