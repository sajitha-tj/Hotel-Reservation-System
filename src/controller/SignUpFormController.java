package controller;

import bo.BoFactory;
import bo.custom.LogInDataBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.LogInDataDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {


    public AnchorPane signInFormContext;
    public JFXTextField txtName;
    public JFXTextField txtTelNo;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;
    public JFXButton btnSignUp;

    LogInDataBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.LOG_IN);

    public void initialize(){
        txtConfirmPassword.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!txtName.getText().isEmpty() && !txtTelNo.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty()){
                btnSignUp.setDisable(false);
            }
        }));
    }

    public void backToLogIn(ActionEvent actionEvent) {
        openUI("LogInForm");
    }

    public void signUp(ActionEvent actionEvent) {
        int nextId =0;
        try {
            nextId = bo.getNextId();
        } catch (Exception throwables) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again later.\nError:"+"\n"+throwables.getMessage()).show();
            return;
        }

        if(txtPassword.getText().equals(txtConfirmPassword.getText())) {

            try {
                if (bo.saveUser(new LogInDataDTO(
                        nextId, txtName.getText(), txtTelNo.getText(), txtAddress.getText(), txtEmail.getText(), "not-Defined", "Not-Allowed", txtPassword.getText().hashCode()
                ))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Signed In successfully. Please wait until an admin confirm request").show();
                    openUI("LogInForm");
                }
            } catch (SQLException throwables) {
                new Alert(Alert.AlertType.WARNING, "User already Exists...").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please confirm your Password").show();
        }
    }


    private void openUI(String link){
        try {
            Stage window = (Stage) signInFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+link+".fxml"))));
            window.centerOnScreen();
        }catch (IOException ioException){
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+ioException.getMessage()).show();
        }
    }
}
