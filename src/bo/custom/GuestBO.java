package bo.custom;

import dto.GuestDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuestBO {
    public boolean saveGuest(GuestDTO dto) throws SQLException,ClassNotFoundException;
    public boolean updateGuest(GuestDTO dto) throws SQLException,ClassNotFoundException;
    public boolean deleteGuest(String id) throws SQLException,ClassNotFoundException;
    public GuestDTO getGuest(String id) throws SQLException,ClassNotFoundException;
    public ArrayList<GuestDTO> searchGuests(String txt) throws SQLException,ClassNotFoundException;
    public String getNextId() throws SQLException,ClassNotFoundException;


}
