package view.tm;

import javafx.scene.control.Button;

public class logInDataTM {
    private int userId;
    private String name;
    private String telNo;
    private String address;
    private String email;
    private String userType;
    private String status;
    private Button btn;

    public logInDataTM() {
    }

    public logInDataTM(int userId, String name, String telNo, String address, String email, String userType, String status, Button btn) {
        this.userId = userId;
        this.name = name;
        this.telNo = telNo;
        this.address = address;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
