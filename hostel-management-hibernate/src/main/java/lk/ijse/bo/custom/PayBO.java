package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

public interface PayBO extends SuperBO {
    boolean updateStatus(String status, String reserveID);
}
