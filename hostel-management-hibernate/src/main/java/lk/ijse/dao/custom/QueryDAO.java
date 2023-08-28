package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<ReserveProjection> getReserveDetail();

    List<ReserveProjection> getReserveByPay(String paid);

    List<ReserveProjection> getReserveByHalfPay();

    List<ReserveProjection> getReserveByStudentID(String studentID);

    ReserveProjection getReserveByPayResID(String reserveID);

    List<ReserveProjection> getReserveByRoomID(String roomID);
}
