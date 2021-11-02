package dao.custom;

import entity.BillData;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BillDataDao {
    boolean save(BillData billData) throws SQLException,ClassNotFoundException;
    boolean updateOnCheckOut(BillData billData) throws SQLException,ClassNotFoundException;
    ArrayList<BillData> search(String text) throws SQLException,ClassNotFoundException;
    ArrayList<BillData> searchCheckedInRooms(String text) throws SQLException,ClassNotFoundException;
    int getNextId()throws SQLException,ClassNotFoundException;
}
