package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;

import java.util.List;

public interface RoomDAO extends SuperDAO {
    List<String> getRoomID();

    List<String> getRoomType();
}
