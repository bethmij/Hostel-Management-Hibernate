package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.ReserveDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ROOM);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RESERVATION);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public List<String> getRoomID() {
        return roomDAO.getRoomID();

    }

    @Override
    public List<String> getStudentID() {
        System.out.println("sdvgfedsrgesrdgdfgdfdbdfbdfgersdgHELOOOOOOOO");
        return studentDAO.getStudentID();
    }

    @Override
    public RoomDTO getRoombyID(String roomID) {
        Room room =  roomDAO.getRoom(roomID);
        return new RoomDTO(room.getTypeId(),room.getType(),room.getKeyMoney(),room.getQty());
    }

    @Override
    public int getUsedRoom() {
        return reserveDAO.getUsedRoomCount();
    }

    @Override
    public String getStuName(String stuID) {
        return studentDAO.getStuName(stuID);
    }
}
