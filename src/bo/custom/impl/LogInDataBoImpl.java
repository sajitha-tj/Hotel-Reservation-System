package bo.custom.impl;

import bo.custom.LogInDataBo;
import dao.DaoFactory;
import dao.custom.LogInDataDao;
import dto.LogInDataDTO;
import entity.LogInData;

import java.sql.SQLException;
import java.util.ArrayList;

public class LogInDataBoImpl implements LogInDataBo {

    LogInDataDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.LOG_IN);

    @Override
    public boolean saveUser(LogInDataDTO dto) throws SQLException, ClassNotFoundException {
        return dao.save(
                new LogInData(
                        dto.getUserId(),dto.getName(),dto.getTelNo(),dto.getAddress(),dto.getEmail(),dto.getUserType(),dto.getStatus(),dto.getPassword()
                )
        );
    }

    @Override
    public ArrayList<LogInDataDTO> search(String text) throws SQLException, ClassNotFoundException {
        ArrayList<LogInData> all = dao.search(text);
        ArrayList<LogInDataDTO> dtoList = new ArrayList<>();
        for(LogInData l : all){
            dtoList.add(new LogInDataDTO(
                    l.getUserId(),l.getName(),l.getTelNo(),l.getAddress(),l.getEmail(),l.getUserType(),l.getStatus(),l.getPassword()
            ));
        }
        return dtoList;
    }

    @Override
    public int getLogIn(String email, int password) throws SQLException, ClassNotFoundException {
        return dao.getLogIn(email, password);
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        return dao.getNextId();
    }

    @Override
    public boolean updateUser(LogInDataDTO dto) throws SQLException, ClassNotFoundException {
        return dao.update(new LogInData(
                dto.getUserId(),dto.getName(),dto.getTelNo(),dto.getAddress(),dto.getEmail(),dto.getUserType(),dto.getStatus(),dto.getPassword()
        ));
    }

    @Override
    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }
}
