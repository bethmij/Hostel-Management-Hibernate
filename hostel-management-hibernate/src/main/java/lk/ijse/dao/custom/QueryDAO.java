package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<ReserveProjection> getReserveDetail();

    ReserveProjection getReserveByID(String reserveID);
}
