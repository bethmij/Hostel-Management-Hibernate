package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student, String> {

    List<String> getStudentID();

    String getStuName(String stuID);

    int getStudentCount();
}
