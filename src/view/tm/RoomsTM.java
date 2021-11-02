package view.tm;

import javafx.scene.control.Button;

public class RoomsTM {
    private String roomNo;
    private String roomType;
    private String price;
    private String availability;
    private Button btn;

    public RoomsTM() {
    }

    public RoomsTM(String roomNo, String roomType, String price, String availability, Button btn) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.price = price;
        this.availability = availability;
        this.btn = btn;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
