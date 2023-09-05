package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.projection.ReserveProjection;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    Session session;

    @Override
    public List<ReserveProjection> getReserveDetail() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty) FROM Reservation rs JOIN rs.room r JOIN rs.student s ORDER BY rs.id ASC ");
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public List<ReserveProjection> getReserveByPay(String paid) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE rs.status = :status ORDER BY rs.id ASC ");
        query.setParameter("status",paid);
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public List<ReserveProjection> getReserveByHalfPay() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE rs.status LIKE 'Half Paid%' ORDER BY rs.id ASC ");
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public List<ReserveProjection> getReserveByStudentID(String studentID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE s.id = :stuID ORDER BY rs.id ASC ");
        query.setParameter("stuID",studentID);
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public ReserveProjection getReserveByPayResID(String reserveID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE rs.id = :resID ORDER BY rs.id ASC ");
        query.setParameter("resID",reserveID);
        ReserveProjection reserveProjection = ReserveProjection.class.cast(query.getSingleResult());
        session.close();
        return reserveProjection;
    }

    @Override
    public List<ReserveProjection> getReserveByRoomID(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE r.id = :roomID ORDER BY rs.id ASC ");
        query.setParameter("roomID",roomID);
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public String getEmail(String reserveID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query<String> query = session.createQuery("SELECT s.email FROM Reservation rs JOIN rs.student s WHERE rs.reserveID = :resID  ",String.class);
        query.setParameter("resID",reserveID);
        String email = query.uniqueResult();
        session.close();
        return email;
    }
}
