package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User, String> {

    boolean updatePic(byte[] imagePath, String userName);

    boolean updateUserName(String text, String userName);

    boolean updatePassword(String text, String userName);

    boolean delete(String userID);

    List<String> getUserNameList();

    String getPassword(String userName);

    String getEmail(String userName);
}
