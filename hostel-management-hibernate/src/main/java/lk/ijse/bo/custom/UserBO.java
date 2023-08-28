package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);
}
