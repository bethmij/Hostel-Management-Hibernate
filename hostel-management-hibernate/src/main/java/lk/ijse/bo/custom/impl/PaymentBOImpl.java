package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RESERVATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ROOM);

    @Override
    public List<ReserveProjection> getReserveDetail() {
        return queryDAO.getAll();
    }

    @Override
    public List<ReserveProjection> getReservebyStudentID(String studentID) {
        return queryDAO.getReserveByStudentID(studentID);
    }

    @Override
    public List<ReserveProjection> getReservebyPay(String paid) {
        return queryDAO.getReserveByPay(paid);
    }

    @Override
    public List<ReserveProjection> getReservebyHalfPay() {
        return queryDAO.getReserveByHalfPay();
    }

    @Override
    public ReserveProjection getReservebyReserveID(String reserveID) {
        return queryDAO.search(reserveID);
    }

    @Override
    public List<ReserveProjection> getReservebyRoomID(String roomID) {
        return queryDAO.getReserveByRoomID(roomID);
    }

    @Override
    public boolean deleteReservation(String reservationID) {
        return reservationDAO.delete(reservationID);
    }

    @Override
    public List<String> getStudentList() {
        return studentDAO.getStudentID();
    }

    @Override
    public List<String> getRoomList() {
        return roomDAO.getRoomID();
    }

    @Override
    public List<String> getreserveList() {
        return reservationDAO.reserveList();
    }
}
