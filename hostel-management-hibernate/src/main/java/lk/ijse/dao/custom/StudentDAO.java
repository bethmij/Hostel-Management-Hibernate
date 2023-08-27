package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO {
    boolean saveStudent(Student student);

    Student getStudent(String text);

    boolean deleteStudent(Student student);

    List<Student> getAllStudent();

    boolean updateStudent(Student student);
}
