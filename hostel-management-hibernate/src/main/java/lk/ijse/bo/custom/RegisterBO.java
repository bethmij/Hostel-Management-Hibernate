package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StudentDTO;

public interface RegisterBO extends SuperBO {

    boolean saveStudent(StudentDTO studentDTO);

    boolean updateStudent(StudentDTO studentDTO);
}
