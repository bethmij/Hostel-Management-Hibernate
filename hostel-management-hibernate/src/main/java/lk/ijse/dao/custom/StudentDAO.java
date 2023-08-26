package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Student;

public interface StudentDAO extends SuperDAO {
    boolean saveStudent(Student student);
}
