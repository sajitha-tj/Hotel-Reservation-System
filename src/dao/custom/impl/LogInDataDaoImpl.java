package dao.custom.impl;

import bo.UserData;
import dao.CrudUtil;
import dao.custom.LogInDataDao;
import dto.LogInDataDTO;
import entity.LogInData;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogInDataDaoImpl implements LogInDataDao {
    @Override
    public boolean save(LogInData logInData) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO LogInData VALUES (?,?,?,?,?,?,?,?)",
                logInData.getUserId(),logInData.getName(),logInData.getTelNo(),logInData.getAddress(),logInData.getEmail(),logInData.getUserType(),logInData.getStatus(),logInData.getPassword()
        );
    }

    @Override
    public ArrayList<LogInData> search(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute(
                "SELECT * FROM LogInData Where name LIKE '%"+text+"%' OR userId LIKE '%"+text+"%' OR telNo LIKE '%"+text+"%' OR email LIKE '%"+text+"%'"
        );
        ArrayList<LogInData> list = new ArrayList<>();
        while (set.next()){
            list.add(new LogInData(
                    set.getInt(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7),
                    0
            ));
        }
        return list;
    }

    @Override
    public int getLogIn(String email, int password) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM LogInData WHERE email=? AND password=?",email,password);
        if(set.next()){
            if(set.getString(7).equalsIgnoreCase("Allowed")){

                UserData.getUser().setLogInData(new LogInDataDTO(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6),
                        set.getString(7),
                        set.getInt(8)
                ));
                return 2;
            }else{
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT userId FROM LogInData ORDER BY userId DESC LIMIT 1");
        if(set.next()){
            int id = set.getInt(1);
            return (id+1);

        }else {
            return 1;
        }
    }

    @Override
    public boolean update(LogInData logInData) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE LogInData SET name=?, telNo=?, address=?, email=?, userType=?, status=? WHERE userId=?",
                logInData.getName(),logInData.getTelNo(),logInData.getAddress(),logInData.getEmail(),logInData.getUserType(),logInData.getStatus(),logInData.getUserId()
        );
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "DELETE FROM LogInData WHERE userId=?",id
        );
    }
}
