package lk.ijse.dao.custom;

import javafx.scene.control.TextField;
import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.User;

public interface UserDAO extends SuperDAO {
    boolean saveUser(User user);

    User getUser(String txtUserName);
}
