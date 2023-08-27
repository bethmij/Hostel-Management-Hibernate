package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;

public interface ReserveDAO extends SuperDAO  {
    int getUsedRoomCount();
}
