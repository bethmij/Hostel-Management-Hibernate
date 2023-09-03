package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.User;

import java.util.List;

public interface UserDAO extends SuperDAO {
    boolean saveUser(User user);

    User getUser(String txtUserName);

    boolean updatePic(byte[] imagePath, String userName);

    boolean updateUserName(String text, String userName);

    boolean updatePassword(String text, String userName);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    List<String> getUserNameList();

    String getPassword(String userName);
}
