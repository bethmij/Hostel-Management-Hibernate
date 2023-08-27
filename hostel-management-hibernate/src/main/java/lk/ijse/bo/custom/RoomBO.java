package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.util.List;

public interface RoomBO extends SuperBO {
    List<String> getRoomID();

    List<String> getRoomType();
}
