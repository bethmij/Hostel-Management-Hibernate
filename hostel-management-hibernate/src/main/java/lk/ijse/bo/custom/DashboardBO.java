package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

public interface DashboardBO extends SuperBO {
    int getTotalStudent();

    int getTotalRooms();

    int getReservedCount();

    int getTotalRoomsSep(String id);

    int getReservedRoomsSep(String s);
}
