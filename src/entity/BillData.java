package entity;

import java.time.LocalDate;
import java.util.Date;

public class BillData {
    private int billId;
    private String guestId;
    private String guestName;
    private String roomNo;
    private int noOfAdults;
    private int noOfChildren;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int noOfDays;
    private double totalCharge;
    private double otherCharges;
    private double subTotal;
    private double discount;
    private double total;
    private double amountPaid;
    private double balance;
    private String status;

    public BillData() {
    }

    public BillData(int billId, String guestId, String guestName, String roomNo, int noOfAdults, int noOfChildren, LocalDate checkInDate, LocalDate checkOutDate, int noOfDays, double totalCharge, double otherCharges, double subTotal, double discount, double total, double amountPaid, double balance, String status) {
        this.billId = billId;
        this.guestId = guestId;
        this.guestName = guestName;
        this.roomNo = roomNo;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.noOfDays = noOfDays;
        this.totalCharge = totalCharge;
        this.otherCharges = otherCharges;
        this.subTotal = subTotal;
        this.discount = discount;
        this.total = total;
        this.amountPaid = amountPaid;
        this.balance = balance;
        this.status = status;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
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

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
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
