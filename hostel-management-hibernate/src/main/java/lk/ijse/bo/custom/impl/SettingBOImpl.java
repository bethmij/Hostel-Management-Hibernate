package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SettingBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;

public class SettingBOImpl implements SettingBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean updatePic(byte[] imagePath, String userName) {
        return userDAO.updatePic(imagePath, userName);
    }

    @Override
    public boolean updateUserName(String text, String userName) {
        return userDAO.updateUserName(text, userName);
    }

    @Override
    public boolean updatePassword(String text, String userName) {
        return userDAO.updatePassword(text, userName);
    }

    @Override
    public boolean deleteUser(User user) {
        return userDAO.deleteUser(user);
    }
}
