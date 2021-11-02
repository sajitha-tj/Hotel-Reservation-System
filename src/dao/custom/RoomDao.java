package dao.custom;

import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDao {
    boolean save(Room room) throws SQLException,ClassNotFoundException;
    boolean update(Room room) throws SQLException,ClassNotFoundException;
    boolean updateAvailabilityOnCheckIn(String roomNo) throws SQLException,ClassNotFoundException;
    boolean updateAvailabilityOnCheckOut(String roomNo) throws SQLException,ClassNotFoundException;
    boolean delete(String roomNo) throws SQLException,ClassNotFoundException;
    ArrayList<Room> search(String text) throws SQLException,ClassNotFoundException;
    ArrayList<String> searchRoomNo(String type) throws SQLException,ClassNotFoundException;
    Room get(String roomNo) throws SQLException,ClassNotFoundException;

}
