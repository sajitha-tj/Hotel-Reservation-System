package bo.custom;

import java.sql.SQLException;

public interface LogInDataBo {
    boolean saveUser(String email, int password) throws SQLException,ClassNotFoundException;
    boolean getLogIn(String email, int password) throws SQLException,ClassNotFoundException;

}
