package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.LogInDataDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInDataDaoImpl implements LogInDataDao {
    @Override
    public boolean save(String email, int password) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO LogInData VALUES (?,?)",
                email, password
        );
    }

    @Override
    public boolean getLogIn(String email, int password) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM LogInData WHERE email=? AND password=?",email,password);
        if(set.next()){
            return true;
        }
        return false;
    }
}
