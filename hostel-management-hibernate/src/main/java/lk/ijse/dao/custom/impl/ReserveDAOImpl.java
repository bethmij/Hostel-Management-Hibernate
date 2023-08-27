package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.ReserveDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ReserveDAOImpl implements ReserveDAO {
    Session session;

    @Override
    public int getUsedRoomCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT count(r.room) FROM Reservation as r");
        return  (int) query.getSingleResult();
    }
}
