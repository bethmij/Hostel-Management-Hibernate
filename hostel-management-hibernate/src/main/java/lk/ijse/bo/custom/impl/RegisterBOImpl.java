package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

public class RegisterBOImpl implements RegisterBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);

    public boolean saveStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getName(), studentDTO.getAddress(),
                studentDTO.getTel1(), studentDTO.getTel2(), studentDTO.getEmail(), studentDTO.getDob(), studentDTO.getGender());
        return studentDAO.saveStudent(student);
    }
}
