package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

    @Override
    public Student getStudent(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        return session.get(Student.class,id);
    }

    @Override
    public boolean deleteStudent(Student student) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(student);
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
    public List<Student> getAllStudent() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery(" FROM Student ");
        return query.list();
    }

    @Override
    public boolean updateStudent(Student student) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(student);
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
    public List<String> getStudentID() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT s.id FROM Student as s");
        return  query.list();
    }

    @Override
    public String getStuName(String stuID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT s.name FROM Student as s WHERE s.id = :stID");
        query.setParameter("stID",stuID);
        return (String) query.getSingleResult();
    }
}
