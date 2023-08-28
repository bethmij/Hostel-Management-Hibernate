package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public List<ReserveProjection> getReserveDetail() {
        return queryDAO.getReserveDetail();
    }
}
