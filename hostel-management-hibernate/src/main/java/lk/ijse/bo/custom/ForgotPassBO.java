package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.User;

public interface ForgotPassBO extends SuperBO {
    boolean updatePassword(String hashed, String userName);
}
