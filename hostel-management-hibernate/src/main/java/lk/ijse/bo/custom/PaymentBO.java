package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

public interface PaymentBO extends SuperBO {
    List<ReserveProjection> getReserveDetail();

    List<ReserveProjection> getReservebyStudentID(String studentID);

    List<ReserveProjection> getReservebyPay(String paid);

    List<ReserveProjection> getReservebyHalfPay();

    ReserveProjection getReservebyReserveID(String reserveID);

    List<ReserveProjection> getReservebyRoomID(String roomID);

    boolean deleteReservation(ReservationDTO reservationDTO);
}
