package controller;

import bo.BoFactory;
import bo.custom.LogInDataBo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            if (bo.getLogIn(txtEmail.getText(), txtPassword.getText().hashCode())){
                goToDashBoard();
            }else {
                new Alert(Alert.AlertType.WARNING,"Incorrect email or password").show();
            }

        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void goToDashBoard(){
        try {
            Stage window = (Stage) logInFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
            window.centerOnScreen();
        }catch (IOException ioException){
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+ioException.getMessage()).show();
        }
    }
}
