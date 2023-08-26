package lk.ijse.bo;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{}

//    public <T extends SuperBO> T getBO (BOType boType){
//
//    }
}
