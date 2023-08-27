package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDAOImpl implements StudentDAO {
    Session session;

    public StudentDAOImpl() {
        session = SessionFactoryConfig.getInstance().getSession();
    }

    @Override
    public boolean saveStudent(Student student){
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(student);
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
