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
    public boolean save(Student student){
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
    public Student search(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Student student = session.get(Student.class,id);
        session.close();
        return student;
    }

    @Override
    public boolean delete(String studentID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Student student = session.get(Student.class,studentID);
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
    public List<Student> getAll() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery(" FROM Student ");
        List<Student> studentList = query.list();
        session.close();
        return studentList;
    }

    @Override
    public boolean update(Student student) {
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
        Query query = session.createQuery("SELECT s.id FROM Student  s");
        List<String> studentID = query.list();
        session.close();
        return studentID;
    }

    @Override
    public String getStuName(String stuID) {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT s.name FROM Student  s WHERE s.id = :stID");
        query.setParameter("stID",stuID);
        String name = (String) query.getSingleResult();
        session.close();
        return name;
    }

    @Override
    public int getStudentCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        Query query = session.createQuery("SELECT count(s.studentID) FROM Student  s ");
        Long countResult = (Long) query.getSingleResult();
        session.close();
        return countResult.intValue();

    }
}
