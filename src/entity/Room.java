package entity;

public class Room {
    private String roomNo;
    private String roomType;
    private String price;
    private String availability;

    public Room() {
    }

    public Room(String roomNo, String roomType, String price, String availability) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.price = price;
        this.availability = availability;
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
}
