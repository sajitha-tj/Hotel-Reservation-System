package bo.custom;

import dto.LogInDataDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LogInDataBo {
    boolean saveUser(LogInDataDTO dto) throws SQLException,ClassNotFoundException;
    ArrayList<LogInDataDTO> search(String text) throws SQLException,ClassNotFoundException;
    int getLogIn(String email, int password) throws SQLException,ClassNotFoundException;
    int getNextId() throws SQLException,ClassNotFoundException;
    boolean updateUser(LogInDataDTO dto) throws SQLException,ClassNotFoundException;
    boolean deleteUser(int id) throws SQLException,ClassNotFoundException;

}
