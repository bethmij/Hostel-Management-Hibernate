package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.projection.ReserveProjection;

import java.util.List;

public interface PaymentBO extends SuperBO {
    List<ReserveProjection> getReserveDetail();
}
