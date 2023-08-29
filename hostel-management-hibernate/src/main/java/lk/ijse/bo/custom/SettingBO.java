package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.User;

public interface SettingBO extends SuperBO {
    boolean updatePic(byte[] imagePath, String userName);

    boolean updateUserName(String text, String userName);

    boolean updatePassword(String text, String userName);

    boolean deleteUser(User user);
}
