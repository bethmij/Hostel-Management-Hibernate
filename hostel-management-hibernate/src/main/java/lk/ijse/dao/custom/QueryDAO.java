package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

public interface QueryDAO extends CrudDAO<ReserveProjection,String> {
    List<ReserveProjection> getReserveByPay(String paid);

    List<ReserveProjection> getReserveByHalfPay();

    List<ReserveProjection> getReserveByStudentID(String studentID);

    List<ReserveProjection> getReserveByRoomID(String roomID);

    String getEmail(String reserveID);

    List<String> getReserveIDbyStudentID(String stuID);

    List<String> getReserveIDbyRoomID(String roomID);
}
