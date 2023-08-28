package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        DASHBOARD,LOGIN,MANAGE,PAYMENT,REGISTER,RESERVE,ROOM,SETTING,USER
    }

    public <T extends SuperBO> T getBO (BOType boType){
        switch (boType){
            case DASHBOARD:
                return (T) new DashboardBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            case MANAGE:
                return (T) new ManageBOImpl();
            case PAYMENT:
                return (T) new PaymentBOImpl();
            case REGISTER:
                return (T) new RegisterBOImpl();
            case RESERVE:
                return (T) new ReservationBOImpl();
            case ROOM:
                return (T) new RoomBOImpl();
            case SETTING:
                return (T) new SettingBOImpl();
            case USER:
                return (T) new UserBOImpl();
            default:
                return null;
        }
    }
}
