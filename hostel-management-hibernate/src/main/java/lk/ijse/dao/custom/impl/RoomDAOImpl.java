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
        Query query = session.createQuery("SELECT r.id FROM Room  r");
        List<String> roomId = query.list();
        session.close();
        return  roomId;
    }

    @Override
    public List<String> getRoomType() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT r.type FROM Room  r");
        List<String> roomType = query.list();
        session.close();
        return  roomType;
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
        List<Room> room = query.list();
        session.close();
        return  room;
    }

    @Override
    public Room getRoom(String roomID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Room room = session.get(Room.class,roomID);
        session.close();
        return room;
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

    @Override
    public boolean updateRoom(Room room) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(room);
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
    public int getTotalRoomCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT sum(r.qty) FROM Room r ");
        Long countResult = (Long) query.getSingleResult();
        session.close();
        return countResult.intValue();
    }

    @Override
    public int getTotalRoomsSep(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT r.qty FROM Room r WHERE r.id= :roomid");
        query.setParameter("roomid",id);
        int qty = (int) query.uniqueResult();
        session.close();
        return qty;
    }


}
