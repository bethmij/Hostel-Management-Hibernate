package lk.ijse.bo.custom.impl;

import javafx.scene.control.TextField;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;

import java.util.List;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public User getUser(String txtUserName) {
        return userDAO.getUser(txtUserName);
    }

    @Override
    public List<String> getUserNameList() {
        return userDAO.getUserNameList();
    }

    @Override
    public String getPassword(String userName) {
        return userDAO.getPassword(userName);
    }

    @Override
    public String getUserEmail(String userName) {
        return userDAO.getEmail(userName);
    }
}
