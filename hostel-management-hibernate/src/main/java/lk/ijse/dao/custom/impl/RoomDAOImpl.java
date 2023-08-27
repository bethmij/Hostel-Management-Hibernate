package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.RoomDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    Session session;

    public RoomDAOImpl(){
        session = SessionFactoryConfig.getInstance().getSession();
    }

    @Override
    public List<String> getRoomID() {
        Query query = session.createQuery("SELECT r.id FROM Room as r");
        return  query.list();
    }

    @Override
    public List<String> getRoomType() {
        Query query = session.createQuery("SELECT r.type FROM Room as r");
        return query.list();
    }
}
