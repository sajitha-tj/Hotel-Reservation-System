package bo.custom.impl;

import bo.custom.GuestBO;
import dao.DaoFactory;
import dao.custom.GuestDao;
import dto.GuestDTO;
import entity.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestBoImpl implements GuestBO {

    GuestDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.GUEST);
    @Override
    public boolean saveGuest(GuestDTO dto) throws SQLException, ClassNotFoundException {
        return dao.save(
                new Guest(dto.getGuestId(),dto.getName(),dto.getNicNo(),dto.getEmail(),dto.getTelNo(),dto.getState(),dto.getAddress())
        );
    }

    @Override
    public boolean updateGuest(GuestDTO dto) throws SQLException, ClassNotFoundException {
        return dao.update(
                new Guest(dto.getGuestId(),dto.getName(),dto.getNicNo(),dto.getEmail(),dto.getTelNo(),dto.getState(),dto.getAddress())
        );
    }

    @Override
    public boolean deleteGuest(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public GuestDTO getGuest(String id) throws SQLException, ClassNotFoundException {
        Guest g = dao.get(id);
        if(g==null){
            return null;
        }
        return new GuestDTO(
                g.getGuestId(),g.getName(),g.getNicNo(),g.getEmail(),g.getTelNo(),g.getState(),g.getAddress()
        );
    }

    @Override
    public ArrayList<GuestDTO> searchGuests(String txt) throws SQLException, ClassNotFoundException {
        ArrayList<Guest> all = dao.search(txt);
        ArrayList<GuestDTO> dtoList = new ArrayList<>();
        for(Guest g:all){
            dtoList.add(new GuestDTO(
                    g.getGuestId(),g.getName(),g.getNicNo(),g.getEmail(),g.getTelNo(),g.getState(),g.getAddress()
            ));
        }
        return dtoList;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return dao.getNextId();
    }

}
