package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Room;

import java.util.List;

public interface RoomDAO extends SuperDAO {
    List<String> getRoomID();

    List<String> getRoomType();

    boolean saveRoom(Room room);

    List<Room> getRoomDetails();

    Room getRoom(String roomID);

    boolean deleteRoom(String roomID);

    boolean updateRoom(Room room);

    int getTotalRoomCount();

    int getTotalRoomsSep(String id);
}
