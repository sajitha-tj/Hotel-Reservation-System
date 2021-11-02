package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BillDataDao;
import entity.BillData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillDataDaoImpl implements BillDataDao {
    @Override
    public boolean save(BillData billData) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO BillData VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                billData.getBillId(),billData.getGuestId(),billData.getGuestName(),billData.getRoomNo(),billData.getNoOfAdults(),billData.getNoOfChildren(),billData.getCheckInDate(),billData.getCheckOutDate(),billData.getNoOfDays(),billData.getTotalCharge(),billData.getOtherCharges(),billData.getSubTotal(),billData.getDiscount(),billData.getTotal(),billData.getAmountPaid(),billData.getBalance(),billData.getStatus()
        );
    }

    @Override
    public boolean updateOnCheckOut(BillData billData) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE BillData SET checkOutDate=?, noOfDays=?, totalCharge=?, otherCharges=?, subTotal=?, discount=?, total=?, amountPaid=?, balance=?, status=? WHERE billId=?",
                billData.getCheckOutDate(),billData.getNoOfDays(),billData.getTotalCharge(),billData.getOtherCharges(),billData.getSubTotal(),billData.getDiscount(),billData.getTotal(),billData.getAmountPaid(),billData.getBalance(),billData.getStatus(),billData.getBillId()
        );
    }

    @Override
    public ArrayList<BillData> search(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM BillData WHERE roomNo LIKE '%" + text + "%' OR guestName LIKE '%"+text+"%'");
        ArrayList<BillData> list = new ArrayList<>();
        while (set.next()){
            list.add(new BillData(
                    set.getInt(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5),
                    set.getInt(6),
                    set.getDate(7).toLocalDate(),
                    set.getDate(8).toLocalDate(),
                    set.getInt(9),
                    set.getDouble(10),
                    set.getDouble(11),
                    set.getDouble(12),
                    set.getDouble(13),
                    set.getDouble(14),
                    set.getDouble(15),
                    set.getDouble(16),
                    set.getString(17)
            ));
        }
        return list;
    }

    @Override
    public ArrayList<BillData> searchCheckedInRooms(String text) throws SQLException, ClassNotFoundException {
        ResultSet set=null;
        if(text.isEmpty()){
            set = CrudUtil.execute("SELECT * FROM BillData WHERE status='Checked-in'");
        }else {
            set = CrudUtil.execute("SELECT * FROM BillData WHERE (roomNo LIKE '%" + text + "%' OR guestName LIKE '%"+text+"%') AND status = 'Checked-in'");
        }

        ArrayList<BillData> list = new ArrayList<>();
        while (set.next()){
            list.add(new BillData(
                    set.getInt(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5),
                    set.getInt(6),
                    set.getDate(7).toLocalDate(),
                    set.getDate(8).toLocalDate(),
                    set.getInt(9),
                    set.getDouble(10),
                    set.getDouble(11),
                    set.getDouble(12),
                    set.getDouble(13),
                    set.getDouble(14),
                    set.getDouble(15),
                    set.getDouble(16),
                    set.getString(17)
            ));
        }
        return list;
    }

    @Override
    public int getNextId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT billId FROM BillData ORDER BY billId DESC LIMIT 1");
        if(set.next()){
            int id = set.getInt(1);
            return (id+1);

        }else {
            return 1;
        }
    }
}
