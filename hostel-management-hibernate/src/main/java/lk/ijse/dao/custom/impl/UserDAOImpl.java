package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

    @Override
    public boolean updatePic(byte[] imagePath, String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE User u SET u.image= :image WHERE u.userName = :userName");
            query.setParameter("image", imagePath);
            query.setParameter("userName", userName);
            query.executeUpdate();
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
    public boolean updateUserName(String text, String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE User u SET u.userName= :user WHERE u.userName = :userName");
            query.setParameter("user", text);
            query.setParameter("userName", userName);
            query.executeUpdate();
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
    public boolean updatePassword(String text, String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("UPDATE User u SET u.password= :pass WHERE u.userName = :userName");
            query.setParameter("pass", text);
            query.setParameter("userName", userName);
            query.executeUpdate();
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
    public boolean updateUser(User user) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(user);
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
    public boolean deleteUser(User user) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(user);
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
    public List<String> getUserNameList() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT u.userName FROM User u");
        List<String> userName = query.list();
        session.close();
        return userName;
    }

    @Override
    public String getPassword(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT u.password FROM User u WHERE u.userName = :userName");
        query.setParameter("userName", userName);
        String password =  (String) query.uniqueResult();
        session.close();
        return password;
    }
}
