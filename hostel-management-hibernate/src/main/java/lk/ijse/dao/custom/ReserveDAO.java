package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Reservation;

public interface ReserveDAO extends SuperDAO  {
    int getUsedRoomCount(String roomID);

    String getNextID();

    boolean reserveRoom(Reservation reservation);
}
