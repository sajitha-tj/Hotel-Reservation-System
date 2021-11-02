package dao;

import dao.custom.impl.BillDataDaoImpl;
import dao.custom.impl.GuestDaoImpl;
import dao.custom.impl.LogInDataDaoImpl;
import dao.custom.impl.RoomDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public enum DaoType{
        GUEST, ROOM, BILL_DATA, LOG_IN
    }
    public static DaoFactory getInstance(){
        if(daoFactory==null){
            daoFactory=new DaoFactory();
        }
        return daoFactory;
    }

    public static <T> T getDao(DaoType type){
        switch (type){
            case GUEST:
                return (T) new GuestDaoImpl();
            case ROOM:
                return (T) new RoomDaoImpl();
            case BILL_DATA:
                return (T) new BillDataDaoImpl();
            case LOG_IN:
                return (T) new LogInDataDaoImpl();
            default:
                return null;
        }
    }
}
