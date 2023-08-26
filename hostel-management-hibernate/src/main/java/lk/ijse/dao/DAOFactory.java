package lk.ijse.dao;

import lk.ijse.dao.custom.impl.ReserveDAOImpl;
import lk.ijse.dao.custom.impl.RoomDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        STUDENT, ROOM, RESERVATION, USER
    }

    public SuperDAO getDAO (DAOType daoType){
        switch (daoType){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReserveDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
