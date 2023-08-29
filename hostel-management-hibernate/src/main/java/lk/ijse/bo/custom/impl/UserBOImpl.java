package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) {
        System.out.println(userDTO.getProfilePic());
        User user = new User(userDTO.getUserID(),userDTO.getUserName(),userDTO.getPassword(),userDTO.getEmail(), userDTO.getProfilePic());
        return userDAO.saveUser(user);
    }
}
