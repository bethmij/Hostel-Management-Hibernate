package lk.ijse.dao.custom.impl;

import javafx.scene.control.TextField;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {
    Session session;

    @Override
    public boolean saveUser(User user) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(user);
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
    public User getUser(String txtUserName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT u FROM User u WHERE u.userName = :userName");
        query.setParameter("userName",txtUserName);
        User user = User.class.cast(query.getSingleResult());
        session.close();
        return user;
    }
}
