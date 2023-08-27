package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    Session session;

    public RoomDAOImpl(){
        session = SessionFactoryConfig.getInstance().getSession();
    }

    @Override
    public List<String> getRoomID() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT r.id FROM Room as r");
        return  query.list();
    }

    @Override
    public List<String> getRoomType() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT r.type FROM Room as r");
        return query.list();
    }

    @Override
    public boolean saveRoom(Room room) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(room);
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
    public List<Room> getRoomDetails() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery(" FROM Room ");
        return query.list();
    }

    @Override
    public Room getRoom(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        return session.get(Room.class,roomID);
    }

    @Override
    public boolean deleteRoom(Room room) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(room);
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
