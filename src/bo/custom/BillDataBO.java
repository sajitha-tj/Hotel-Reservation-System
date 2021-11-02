package bo.custom;

import dto.BillDataDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BillDataBO {
    boolean saveBill(BillDataDTO dto) throws SQLException,ClassNotFoundException;
    boolean updateOnCheckOut(BillDataDTO dto) throws SQLException,ClassNotFoundException;
    int getNextId()throws SQLException,ClassNotFoundException;
    ArrayList<BillDataDTO> searchCheckedInRooms(String text) throws SQLException,ClassNotFoundException;
    ArrayList<BillDataDTO> search(String text) throws SQLException,ClassNotFoundException;
}
