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
        Query query = session.createQuery("SELECT new lk.ijse.entity.projection.ReserveProjection(rs.id, s.id, s.name, r.id, r.type, rs.status, r.keyMoney) FROM Reservation rs JOIN rs.room r JOIN rs.student s ");
        return query.list();
    }
}
