package dao.custom;

import entity.LogInData;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LogInDataDao {
    boolean save (LogInData logInData) throws SQLException,ClassNotFoundException;
    ArrayList<LogInData> search(String text) throws SQLException,ClassNotFoundException;
    int getLogIn (String email, int password) throws SQLException,ClassNotFoundException;
    int getNextId() throws SQLException,ClassNotFoundException;
    boolean update (LogInData logInData) throws SQLException,ClassNotFoundException;
    boolean delete (int id) throws SQLException,ClassNotFoundException;

}
