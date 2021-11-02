package bo.custom;

import dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO {
    boolean saveRoom(RoomDTO dto) throws SQLException,ClassNotFoundException;
    boolean updateRoom(RoomDTO dto) throws SQLException,ClassNotFoundException;
    boolean updateAvailabilityOnCheckIn(String roomNo) throws SQLException,ClassNotFoundException;
    boolean updateAvailabilityOnCheckOut(String roomNo) throws SQLException,ClassNotFoundException;
    boolean deleteRoom(String roomNo) throws SQLException,ClassNotFoundException;
    ArrayList<RoomDTO> searchRoom(String txt) throws SQLException,ClassNotFoundException;
    ArrayList<String> searchRoomNo(String type) throws SQLException,ClassNotFoundException;
    RoomDTO  getRoom(String roomNo) throws SQLException,ClassNotFoundException;
}
