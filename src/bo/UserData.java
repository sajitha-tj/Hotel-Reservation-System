package bo;

import dto.LogInDataDTO;

public class UserData {
    private static UserData userData;
    private LogInDataDTO logInDataDTO;

    private UserData(){

    }

    public static UserData getUser(){
        return userData = (userData == null)?(new UserData()):(userData);
    }

    public LogInDataDTO getLogInData(){
        return logInDataDTO;
    }

    public void setLogInData(LogInDataDTO dto){
        this.logInDataDTO = dto;
    }
    public static void clearUserData(){
        userData = null;
    }
}
