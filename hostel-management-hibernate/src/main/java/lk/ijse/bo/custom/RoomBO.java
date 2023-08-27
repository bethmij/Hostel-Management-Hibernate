package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    List<String> getRoomID();

    List<String> getRoomType();

    boolean saveRoom(RoomDTO roomDTO);

    List<RoomDTO> getRoomDetail();

    RoomDTO getRoom(String roomID);

    boolean deleteRoom(RoomDTO room);
}
