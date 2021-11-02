package view.tm;

import java.time.LocalDate;

public class GuestHistoryTM {
    private int billId;
    private String roomNo;
    private String roomType;
    private String guestId;
    private String name;
    private String nicNo;
    private String telNo;
    private int noOfPersons;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int noOfDays;
    private double total;
    private double paid;
    private double balance;
    private String status;

    public GuestHistoryTM() {
    }

    public GuestHistoryTM(int billId, String roomNo, String roomType, String guestId, String name, String nicNo, String telNo, int noOfPersons, LocalDate checkInDate, LocalDate checkOutDate, int noOfDays, double total, double paid, double balance, String status) {
        this.billId = billId;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.guestId = guestId;
        this.name = name;
        this.nicNo = nicNo;
        this.telNo = telNo;
        this.noOfPersons = noOfPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.noOfDays = noOfDays;
        this.total = total;
        this.paid = paid;
        this.balance = balance;
        this.status = status;
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

    public int getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(int noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
