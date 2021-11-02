package bo.custom.impl;

import bo.custom.BillDataBO;
import dao.DaoFactory;
import dao.custom.BillDataDao;
import dto.BillDataDTO;
import entity.BillData;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillDataBoImpl implements BillDataBO{

    BillDataDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.BILL_DATA);
    @Override
    public boolean saveBill(BillDataDTO dto) throws SQLException, ClassNotFoundException {
        return dao.save(
                new BillData(dto.getBillId(), dto.getGuestId(), dto.getGuestName(), dto.getRoomNo(), dto.getNoOfAdults(), dto.getNoOfChildren(), dto.getCheckInDate(), dto.getCheckOutDate(), dto.getNoOfDays(), dto.getTotalCharge(), dto.getOtherCharges(), dto.getSubTotal(), dto.getDiscount(), dto.getTotal(), dto.getAmountPaid(), dto.getBalance(), dto.getStatus())
        );
    }

    @Override
    public boolean updateOnCheckOut(BillDataDTO dto) throws SQLException, ClassNotFoundException {
        return dao.updateOnCheckOut(
                new BillData(dto.getBillId(), dto.getGuestId(), dto.getGuestName(), dto.getRoomNo(), dto.getNoOfAdults(), dto.getNoOfChildren(), dto.getCheckInDate(), dto.getCheckOutDate(), dto.getNoOfDays(), dto.getTotalCharge(), dto.getOtherCharges(), dto.getSubTotal(), dto.getDiscount(), dto.getTotal(), dto.getAmountPaid(), dto.getBalance(), dto.getStatus())
        );
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        return dao.getNextId();
    }

    @Override
    public ArrayList<BillDataDTO> searchCheckedInRooms(String text) throws SQLException, ClassNotFoundException {
        ArrayList<BillData> all = dao.searchCheckedInRooms(text);
        ArrayList<BillDataDTO> dtoList = new ArrayList<>();
        for(BillData b:all){
            dtoList.add(new BillDataDTO(
                    b.getBillId(),b.getGuestId(), b.getGuestName(), b.getRoomNo(),b.getNoOfAdults(),b.getNoOfChildren(),b.getCheckInDate(),b.getCheckOutDate(),b.getNoOfDays(),b.getTotalCharge(),b.getOtherCharges(),b.getSubTotal(),b.getDiscount(),b.getTotal(),b.getAmountPaid(),b.getBalance(),b.getStatus()
            ));
        }
        return dtoList;
    }

    @Override
    public ArrayList<BillDataDTO> search(String text) throws SQLException, ClassNotFoundException {
        ArrayList<BillData> all = dao.search(text);
        ArrayList<BillDataDTO> dtoList = new ArrayList<>();
        for(BillData b:all){
            dtoList.add(new BillDataDTO(
                    b.getBillId(),b.getGuestId(), b.getGuestName(), b.getRoomNo(),b.getNoOfAdults(),b.getNoOfChildren(),b.getCheckInDate(),b.getCheckOutDate(),b.getNoOfDays(),b.getTotalCharge(),b.getOtherCharges(),b.getSubTotal(),b.getDiscount(),b.getTotal(),b.getAmountPaid(),b.getBalance(),b.getStatus()
            ));
        }
        return dtoList;
    }
}
