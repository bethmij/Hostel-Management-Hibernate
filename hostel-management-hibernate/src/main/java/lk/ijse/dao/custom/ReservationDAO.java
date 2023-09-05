package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO extends SuperDAO  {
    int getUsedRoomCount(String roomID);

    String getNextID();

    boolean reserveRoom(Reservation reservation);

    boolean deleteReservation(Reservation reservationDTO);

    boolean updateRoom(Reservation reservation);

    int getReservedCount();

    int getReservedRoomsSep(String s);

    List<String> reserveList();

    boolean updateStatus(String status, String reserveID);

    LocalDate getReservedDate(String reserveID);
}
