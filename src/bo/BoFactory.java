package bo;

import bo.custom.impl.BillDataBoImpl;
import bo.custom.impl.GuestBoImpl;
import bo.custom.impl.LogInDataBoImpl;
import bo.custom.impl.RoomBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){}

    public enum BoType{
        GUEST, ROOM, BILL_DATA, LOG_IN
    }

    public static BoFactory getInstance(){
        if(boFactory==null){
            boFactory=new BoFactory();
        }
        return boFactory;
    }

    public static <T> T getBo(BoType type){
        switch (type){
            case GUEST:
                return (T) new GuestBoImpl();
            case ROOM:
                return (T) new RoomBoImpl();
            case BILL_DATA:
                return (T) new BillDataBoImpl();
            case LOG_IN:
                return (T) new LogInDataBoImpl();
            default:
                return null;
        }
    }
}
