package controller;

import bo.BoFactory;
import bo.custom.LogInDataBo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane logInFormContext;
    public TextField txtEmail;
    public PasswordField txtPassword;

    LogInDataBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.LOG_IN);

    public void login(ActionEvent actionEvent) throws IOException {
        //email: admin@mail.com
        //password: admin1234
        try {
            if (bo.getLogIn(txtEmail.getText(), txtPassword.getText().hashCode()) == 2){
                openUI("DashBoardForm");
            }else if(bo.getLogIn(txtEmail.getText(), txtPassword.getText().hashCode()) == 1){
                new Alert(Alert.AlertType.INFORMATION, "Please wait until an admin approve your request").show();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Incorrect email or password").show();
            }

        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void openUI(String link){
        try {
            Stage window = (Stage) logInFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+link+".fxml"))));
            window.centerOnScreen();
        }catch (IOException ioException){
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+ioException.getMessage()).show();
        }
    }

    public void signUp(ActionEvent actionEvent) {
        openUI("SignUpForm");
    }

    public void onEnterKey(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            ActionEvent a = new ActionEvent();
            login(a);
        }
    }
}
