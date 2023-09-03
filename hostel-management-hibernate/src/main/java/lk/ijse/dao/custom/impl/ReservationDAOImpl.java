package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    Session session;

    @Override
    public int getUsedRoomCount(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT count(rs.id) FROM Reservation  rs JOIN rs.room r WHERE r.id = :roomId");
        query.setParameter("roomId",roomID);
        Long countResult = (Long) query.getSingleResult();
        session.close();
        return countResult.intValue();
    }

    @Override
    public String getNextID() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT r.id FROM Reservation  r ORDER BY r.id DESC");
        query.setMaxResults(1);
        String id = (String) query.uniqueResult();
        session.close();
        return id;

    }

    @Override
    public boolean reserveRoom(Reservation reservation) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(reservation);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteReservation(Reservation reservationDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(reservationDTO);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateRoom(Reservation reservation) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(reservation);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public int getReservedCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT count (r.id) FROM Reservation rs JOIN rs.room r");
        Long countResult = (Long) query.getSingleResult();
        session.close();
        return countResult.intValue();
    }

    @Override
    public int getReservedRoomsSep(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT count(r.id) FROM Reservation rs JOIN rs.room r WHERE r.id=:roomid");
        query.setParameter("roomid",roomID);
        Long countResult = (Long) query.getSingleResult();
        session.close();
        return countResult.intValue();
    }

    @Override
    public List<String> reserveList() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT rs.id FROM Reservation rs");
        List<String> id = query.list();
        session.close();
        return id;
    }

    @Override
    public boolean updateStatus(String status, String reserveID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE Reservation rs SET rs.status = :status WHERE rs.id =:reserveID");
            query.setParameter("status",status);
            query.setParameter("reserveID",reserveID).executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }
}
