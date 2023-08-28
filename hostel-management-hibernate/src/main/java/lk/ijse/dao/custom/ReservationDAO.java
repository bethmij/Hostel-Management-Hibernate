package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;

public interface ReservationDAO extends SuperDAO  {
    int getUsedRoomCount(String roomID);

    String getNextID();

    boolean reserveRoom(Reservation reservation);

    boolean deleteReservation(Reservation reservationDTO);

    boolean updateRoom(Reservation reservation);
}
