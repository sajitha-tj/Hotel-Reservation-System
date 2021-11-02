package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.GuestDao;
import entity.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDaoImpl implements GuestDao {
    public boolean save(Guest guest) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Guests VALUES (?,?,?,?,?,?,?)",
                guest.getGuestId(),guest.getName(),guest.getNicNo(),guest.getEmail(),guest.getTelNo(),guest.getState(),guest.getAddress()
        );
    }

    @Override
    public boolean update(Guest guest) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE Guests SET name=?, nicNo=?, email=?, telNo=?, state=?, address=? WHERE guestId=?",
                guest.getName(),guest.getNicNo(),guest.getEmail(),guest.getTelNo(),guest.getState(),guest.getAddress(),guest.getGuestId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Guests WHERE guestId =?",id);
    }

    @Override
    public Guest get(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Guests WHERE guestId=?",id);
        if(set.next()){
            return new Guest(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7)
            );
        }
        return null;
    }

    public ArrayList<Guest> search(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Guests WHERE guestId LIKE '%"+text+"%' OR name LIKE '%"+text+"%' OR nicNo LIKE '%"+text+"%' OR email LIKE '%"+text+"%' OR telNo LIKE '%"+text+"%' OR state LIKE '%"+text+"%'");
        ArrayList<Guest> list = new ArrayList<>();
        while (set.next()){
            list.add(new Guest(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7)
            ));
        }
        return list;
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT guestId FROM Guests ORDER BY guestId DESC LIMIT 1");
        if(set.next()){
            String id = set.getString(1);
            int tempId = Integer.parseInt(id.split("G")[1]);
            id = "G"+(tempId+1);

            return id;

        }else {
            return "G10001";
        }
    }

}
