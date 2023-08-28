package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ReservationDAOImpl implements ReservationDAO {
    Session session;

    @Override
    public int getUsedRoomCount(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT count(rs.id) FROM Reservation  rs JOIN rs.room r WHERE r.id = :roomId");
        query.setParameter("roomId",roomID);
        Long countResult = (Long) query.getSingleResult();
        return countResult.intValue();
    }

    @Override
    public String getNextID() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT r.id FROM Reservation  r ORDER BY r.id DESC");
        query.setMaxResults(1);
        return (String) query.uniqueResult();

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
}
