package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    int getUsedRoomCount(String roomID);

    String getNextID();

    int getReservedCount();

    int getReservedRoomsSep(String s);

    List<String> reserveList();

    boolean updateStatus(String status, String reserveID);

    LocalDate getReservedDate(String reserveID);


}
