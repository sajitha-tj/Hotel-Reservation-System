package dao.custom;

import java.sql.SQLException;

public interface LogInDataDao {
    boolean save (String email, int password) throws SQLException,ClassNotFoundException;
    boolean getLogIn (String email, int password) throws SQLException,ClassNotFoundException;

}
