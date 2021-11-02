package bo.custom.impl;

import bo.custom.LogInDataBo;
import dao.DaoFactory;
import dao.custom.LogInDataDao;

import java.sql.SQLException;

public class LogInDataBoImpl implements LogInDataBo {

    LogInDataDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.LOG_IN);

    @Override
    public boolean saveUser(String email, int password) throws SQLException, ClassNotFoundException {
        return dao.save(email,password);
    }

    @Override
    public boolean getLogIn(String email, int password) throws SQLException, ClassNotFoundException {
        return dao.getLogIn(email, password);
    }
}
