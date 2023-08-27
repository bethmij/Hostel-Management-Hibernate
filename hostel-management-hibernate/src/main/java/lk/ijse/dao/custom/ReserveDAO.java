package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Reservation;

public interface ReserveDAO extends SuperDAO  {
    int getUsedRoomCount();

    String getNextID();

    boolean reserveRoom(Reservation reservation);
}
