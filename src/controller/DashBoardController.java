package controller;

import bo.UserData;
import com.jfoenix.controls.JFXButton;
import dto.LogInDataDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardController {
    public AnchorPane dashBoardContext;
    public Label lblTopic;
    public JFXButton btnUserManagement;
    public JFXButton btnRooms;
    public Label lblUserName;
    public AnchorPane formContext;

    private LogInDataDTO userData = UserData.getUser().getLogInData();

    public void initialize() throws IOException {
        lblUserName.setText("Hey, "+userData.getName());

        if(!userData.getUserType().equalsIgnoreCase("admin")){
            btnRooms.setDisable(true);
            btnUserManagement.setDisable(true);
        }
        openUI("GuestRegistrationForm");
    }

    public void openGuestRegistration(ActionEvent actionEvent) throws IOException {
        lblTopic.setText("Guest Registration");
        openUI("GuestRegistrationForm");
    }

    public void openCheckIn(ActionEvent actionEvent) throws IOException {
        lblTopic.setText("Guest Check-In");
        openUI("CheckInForm");
    }

    public void openCheckOut(ActionEvent actionEvent) throws IOException {
        lblTopic.setText("Guest Check-Out");
        openUI("CheckOutForm");
    }

    public void openGuestHistory(ActionEvent actionEvent) throws IOException {
        String t = lblTopic.getText();
        lblTopic.setText("Opening Guest Check-Out");
        URL resource = getClass().getResource("../view/GuestHistoryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Guest History");
        primaryStage.show();
        lblTopic.setText(t);
    }

    public void openRooms(ActionEvent actionEvent) throws IOException {
        lblTopic.setText("Room Management");
        openUI("RoomManagementForm");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        UserData.clearUserData();

        Stage window = (Stage) formContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        window.centerOnScreen();
    }

    public void openUserManagement(ActionEvent actionEvent) throws IOException {
        openUI("UserManagementForm");
    }

    public void openUI(String fileName) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/"+fileName+".fxml")));
    }

}
