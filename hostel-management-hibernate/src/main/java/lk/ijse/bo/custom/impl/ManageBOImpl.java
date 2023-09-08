package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ManageBOImpl implements ManageBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public StudentDTO getStudent(String text) {
        Student student = studentDAO.search(text);
        return new StudentDTO(student.getStudentID(),student.getName(),student.getAddress(),
                        student.getTel1(),student.getTel2(),student.getEmail(),student.getDob(),student.getGender());
    }

    @Override
    public boolean deleteStudent(String studentID) {
//        Student student = new Student(studentDTO.getStudentID(),studentDTO.getName(),studentDTO.getAddress(),
//                        studentDTO.getTel1(),studentDTO.getTel2(),studentDTO.getEmail(),studentDTO.getDob(),studentDTO.getGender());
        return studentDAO.delete(studentID);
    }

    @Override
    public List<StudentDTO> getStudentl() {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student studentList:students) {
            studentDTOS.add(new StudentDTO(studentList.getStudentID(),studentList.getName(),studentList.getAddress(),studentList.getTel1(),
                            studentList.getTel2(),studentList.getEmail(),studentList.getDob(),studentList.getGender()));
        }
        return studentDTOS;
    }

    @Override
    public List<String> getStudentID() {
        return studentDAO.getStudentID();
    }

    @Override
    public List<String> getReservebyStudentID(String stuID) {
        return queryDAO.getReserveIDbyStudentID(stuID);
    }

    @Override
    public boolean deleteReservation(String reservationID) {
        return reservationDAO.delete(reservationID);
    }
}
