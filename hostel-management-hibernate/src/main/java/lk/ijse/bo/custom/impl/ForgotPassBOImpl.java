package lk.ijse.bo.custom.impl;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;

public class ForgotPassBOImpl implements lk.ijse.bo.custom.ForgotPassBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean updatePassword(String hashed, String userName) {
        return userDAO.updatePassword(hashed,userName);
    }
}
