package bo.custom.impl;

import bo.custom.RoomBO;
import dao.DaoFactory;
import dao.custom.RoomDao;
import dto.RoomDTO;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBoImpl implements RoomBO {
    RoomDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ROOM);

    @Override
    public boolean saveRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return dao.save(
                new Room(dto.getRoomNo(),dto.getRoomType(),dto.getPrice(),dto.getAvailability())
        );
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return dao.update(
                new Room(dto.getRoomNo(),dto.getRoomType(),dto.getPrice(),dto.getAvailability())
        );
    }

    @Override
    public boolean updateAvailabilityOnCheckIn(String roomNo) throws SQLException, ClassNotFoundException {
        return dao.updateAvailabilityOnCheckIn(roomNo);
    }

    @Override
    public boolean updateAvailabilityOnCheckOut(String roomNo) throws SQLException, ClassNotFoundException {
        return dao.updateAvailabilityOnCheckOut(roomNo);
    }

    @Override
    public boolean deleteRoom(String roomNo) throws SQLException, ClassNotFoundException {
        return dao.delete(roomNo);
    }

    @Override
    public ArrayList<RoomDTO> searchRoom(String txt) throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = dao.search(txt);
        ArrayList<RoomDTO> dtoList = new ArrayList<>();
        for(Room r:all){
            dtoList.add(new RoomDTO(
                    r.getRoomNo(),r.getRoomType(),r.getPrice(),r.getAvailability()
            ));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> searchRoomNo(String type) throws SQLException, ClassNotFoundException {
        return dao.searchRoomNo(type);
    }

    @Override
    public RoomDTO getRoom(String roomNo) throws SQLException, ClassNotFoundException {
        Room r = dao.get(roomNo);
        if(r==null){
            return null;
        }
        return new RoomDTO(
                r.getRoomNo(),r.getRoomType(),r.getPrice(),r.getAvailability()
        );
    }
}
