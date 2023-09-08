package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.projection.ReserveProjection;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    Session session;

    @Override
    public List<ReserveProjection> getAll() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty, rs.date) FROM Reservation rs JOIN rs.room r JOIN rs.student s ORDER BY rs.id ASC ");
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }


    @Override
    public List<ReserveProjection> getReserveByPay(String paid) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty, rs.date) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE rs.status = :status ORDER BY rs.id ASC ");
        query.setParameter("status",paid);
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public List<ReserveProjection> getReserveByHalfPay() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty, rs.date) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE rs.status LIKE 'Half Paid%' ORDER BY rs.id ASC ");
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public List<ReserveProjection> getReserveByStudentID(String studentID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty, rs.date) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE s.id = :stuID ORDER BY rs.id ASC ");
        query.setParameter("stuID",studentID);
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override 
    public boolean delete(String s) {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public ReserveProjection search(String reserveID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty, rs.date) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE rs.id = :resID ORDER BY rs.id ASC ");
        query.setParameter("resID",reserveID);
        ReserveProjection reserveProjection = ReserveProjection.class.cast(query.getSingleResult());
        session.close();
        return reserveProjection;
    }

    @Override
    public List<ReserveProjection> getReserveByRoomID(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney,r.qty, rs.date) FROM Reservation rs JOIN rs.room r JOIN rs.student s WHERE r.id = :roomID ORDER BY rs.id ASC ");
        query.setParameter("roomID",roomID);
        List<ReserveProjection> reserveProjections = query.list();
        session.close();
        return reserveProjections;
    }

    @Override
    public boolean update(ReserveProjection entity)  {
        throw new UnsupportedOperationException("This feature yet to be developed");
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

    @Override
    public boolean save(ReserveProjection entity)  {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public List<String> getReserveIDbyStudentID(String stuID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query<String> query = session.createQuery("SELECT rs.reserveID FROM Reservation rs JOIN rs.student s WHERE s.studentID = :id");
        query.setParameter("id",stuID);
        List<String> id = query.list();
        session.close();
        return id;
    }

    @Override
    public List<String> getReserveIDbyRoomID(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query<String> query = session.createQuery("SELECT rs.reserveID FROM Reservation rs JOIN rs.room r WHERE r.id = :id");
        query.setParameter("id",roomID);
        List<String> id = query.list();
        session.close();
        return id;
    }
}
