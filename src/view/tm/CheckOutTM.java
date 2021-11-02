package view.tm;

import java.time.LocalDate;

public class CheckOutTM {
    private int billId;
    private String roomNo;
    private String roomType;
    private String guestId;
    private String name;
    private String nicNo;
    private String telNo;
    private LocalDate checkInDate;
    private int noOfAdults;
    private int noOfChildren;
    private int noOfDays;

    public CheckOutTM() {
    }

    public CheckOutTM(int billId, String roomNo, String roomType, String guestId, String name, String nicNo, String telNo, LocalDate checkInDate, int noOfAdults, int noOfChildren, int noOfDays) {
        this.billId = billId;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.guestId = guestId;
        this.name = name;
        this.nicNo = nicNo;
        this.telNo = telNo;
        this.checkInDate = checkInDate;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.noOfDays = noOfDays;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }
}
