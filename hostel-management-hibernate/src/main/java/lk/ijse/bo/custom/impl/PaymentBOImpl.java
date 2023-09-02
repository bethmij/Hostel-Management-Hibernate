package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.entity.projection.ReserveProjection;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RESERVATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ROOM);

    @Override
    public List<ReserveProjection> getReserveDetail() {
        return queryDAO.getReserveDetail();
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
        return queryDAO.getReserveByPayResID(reserveID);
    }

    @Override
    public List<ReserveProjection> getReservebyRoomID(String roomID) {
        return queryDAO.getReserveByRoomID(roomID);
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) {
        Room room = new Room(reservationDTO.getRoom().getTypeId());
        Student student = new Student(reservationDTO.getStudent().getStudentID());
        Date date = Date.from(reservationDTO.getDate().atZone(ZoneId.systemDefault()).toInstant());

        Reservation reservation = new Reservation(reservationDTO.getReserveID(),room,student,
                date,reservationDTO.getStatus());

        return reservationDAO.deleteReservation(reservation);
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
