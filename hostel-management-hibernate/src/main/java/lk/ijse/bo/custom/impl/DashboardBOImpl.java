package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DashboardBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.StudentDAO;

public class DashboardBOImpl implements DashboardBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ROOM);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public int getTotalStudent() {
        return studentDAO.getStudentCount();
    }

    @Override
    public int getTotalRooms() {
        return roomDAO.getTotalRoomCount();
    }

    @Override
    public int getReservedCount() {
        return reservationDAO.getReservedCount();
    }

    @Override
    public int getTotalRoomsSep(String id) {
        return roomDAO.getTotalRoomsSep(id);
    }

    @Override
    public int getReservedRoomsSep(String s) {
        return reservationDAO.getReservedRoomsSep(s);
    }
}
