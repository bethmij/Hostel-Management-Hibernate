package lk.ijse.bo.custom.impl;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationDAO;

public class PayBOImpl implements lk.ijse.bo.custom.PayBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public boolean updateStatus(String status, String reserveID) {
        return reservationDAO.updateStatus(status,reserveID);
    }
}
