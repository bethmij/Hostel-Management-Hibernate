package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StudentDTO;

import java.util.List;

public interface ManageBO extends SuperBO {
    StudentDTO getStudent(String id);

    boolean deleteStudent(String studentID);

    List<StudentDTO> getStudentl();

    List<String> getStudentID();

    List<String> getReservebyStudentID(String stuID);

    boolean deleteReservation(String reservationID);
}
