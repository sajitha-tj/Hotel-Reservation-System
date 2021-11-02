package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.RoomDao;
import entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDaoImpl implements RoomDao {
    @Override
    public boolean save(Room room) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Rooms VALUES (?,?,?,?)",
                room.getRoomNo(),room.getRoomType(),room.getPrice(),room.getAvailability()
        );
    }

    @Override
    public boolean update(Room room) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Rooms SET roomType=?, price=?, availability=? WHERE roomNo=?",
                room.getRoomType(),room.getPrice(),room.getAvailability(),room.getRoomNo()
        );
    }

    @Override
    public boolean updateAvailabilityOnCheckIn(String roomNo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Rooms SET availability='Not-Available' WHERE roomNo=?",roomNo);
    }

    @Override
    public boolean updateAvailabilityOnCheckOut(String roomNo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Rooms SET availability='Available' WHERE roomNo=?",roomNo);
    }

    @Override
    public boolean delete(String roomNo) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Rooms WHERE roomNo =?",roomNo);
    }

    @Override
    public ArrayList<Room> search(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Rooms WHERE roomNo LIKE '%"+text+"%'");
        ArrayList<Room> list = new ArrayList<>();
        while (set.next()){
            list.add(new Room(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4)
            ));
        }
        return list;
    }

    @Override
    public ArrayList<String> searchRoomNo(String type) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT roomNo, Availability FROM Rooms WHERE roomType LIKE '"+type+"'");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()){
            if (set.getString(2).equalsIgnoreCase("Available")){
                list.add(
                        set.getString(1)
                );
            }
        }
        return list;
    }

    @Override
    public Room get(String roomNo) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Rooms WHERE roomNo=?",roomNo);
        if(set.next()){
            return new Room(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4)
            );
        }
        return null;
    }
}
