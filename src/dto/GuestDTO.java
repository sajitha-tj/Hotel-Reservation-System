package dto;

public class GuestDTO {
    private String guestId;
    private String name;
    private String nicNo;
    private String email;
    private String telNo;
    private String state;
    private String address;

    public GuestDTO() {
    }

    public GuestDTO(String guestId, String name, String nicNo, String email, String telNo, String state, String address) {
        this.guestId = guestId;
        this.name = name;
        this.nicNo = nicNo;
        this.email = email;
        this.telNo = telNo;
        this.state = state;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
