package lk.ijse.bo.custom;

import javafx.scene.control.TextField;
import lk.ijse.bo.SuperBO;
import lk.ijse.entity.User;

public interface LoginBO extends SuperBO {
    User getUser(String txtName);
}
