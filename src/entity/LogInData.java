package entity;

public class LogInData {
    private int userId;
    private String name;
    private String telNo;
    private String address;
    private String email;
    private String userType;
    private String status;
    private int password;

    public LogInData() {
    }

    public LogInData(int userId, String name, String telNo, String address, String email, String userType, String status, int password) {
        this.userId = userId;
        this.name = name;
        this.telNo = telNo;
        this.address = address;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
