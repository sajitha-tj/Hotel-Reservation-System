package dao.custom;

import entity.Guest;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuestDao {
    boolean save(Guest guest) throws SQLException,ClassNotFoundException;
    boolean update(Guest guest) throws SQLException,ClassNotFoundException;
    boolean delete(String id) throws SQLException,ClassNotFoundException;
    Guest get(String id) throws SQLException,ClassNotFoundException;
    ArrayList<Guest> search(String text) throws SQLException,ClassNotFoundException;
    String getNextId() throws SQLException,ClassNotFoundException;

}
