package controller;

import bo.BoFactory;
import bo.custom.GuestBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.GuestDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.GuestsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestRegistrationFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtTelNo;
    public JFXTextField txtNic;
    public JFXTextField txtState;
    public JFXTextField txtAddress;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colEmail;
    public TableColumn colTelNo;
    public TableColumn colState;
    public TableColumn colAddress;
    public TableColumn colOptions;
    public TableView<GuestsTM> tblGuests;
    public TextField txtSearch;
    public JFXButton btnSubmit;
    public Label lblTopic;

    GuestBO bo = BoFactory.getInstance().getBo(BoFactory.BoType.GUEST);

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nicNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOptions.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadGuests("");
        setNextId();
        //select--
        tblGuests.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setGuestData(newValue);
            }
        });
        //search--
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadGuests(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        txtId.setText(bo.getNextId());
    }

    private void setGuestData(GuestsTM tm){
        btnSubmit.setText("Update");
        lblTopic.setText("Update Info");
        txtId.setText(tm.getGuestId());
        txtName.setText(tm.getName());
        txtNic.setText(tm.getNicNo());
        txtEmail.setText(tm.getEmail());
        txtTelNo.setText(tm.getTelNo());
        txtState.setText(tm.getState());
        txtAddress.setText(tm.getAddress());
    }

    private void loadGuests(String txt) throws SQLException, ClassNotFoundException {
        ArrayList<GuestDTO> allGuests = bo.searchGuests(txt);
        ObservableList<GuestsTM> obList = FXCollections.observableArrayList();

        for(GuestDTO dto: allGuests){
            Button btn = new Button("X");
            obList.addAll(
                    new GuestsTM(
                            dto.getGuestId(),dto.getName(), dto.getNicNo(), dto.getEmail(), dto.getTelNo(), dto.getState(), dto.getAddress(), btn
                    )
            );
            //delete btn--
            btn.setOnAction((e->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you wat to delete this?",
                        ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
                alert.showAndWait();

                if(alert.getResult()==ButtonType.YES){
                    try {

                        if(bo.deleteGuest(dto.getGuestId())){
                            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully").show();
                            loadGuests("");
                        }else {
                            new Alert(Alert.AlertType.WARNING,"Try Again").show();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }

            }));
        }
        tblGuests.setItems(obList);
    }

    public void registerGuest(ActionEvent actionEvent) throws ClassNotFoundException {

        //----Validate----
        if(txtName.getText().isEmpty() || txtNic.getText().isEmpty() || txtTelNo.getText().isEmpty() || txtAddress.getText().isEmpty() || txtState.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please fill required fields").show();
            return;
        }
        //----

        if(btnSubmit.getText().equalsIgnoreCase("Register")){
            //register New
            try {
                if(bo.saveGuest(
                        new GuestDTO(txtId.getText(),txtName.getText(),txtNic.getText(),txtEmail.getText(),txtTelNo.getText(),txtState.getText(),txtAddress.getText())
                )){
                    new Alert(Alert.AlertType.CONFIRMATION,"Registered Successfully").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again :(").show();
                }
            } catch (SQLException throwables) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
                return;
            }
        }else {
            try {
                if(bo.updateGuest(
                        new GuestDTO(txtId.getText(),txtName.getText(),txtNic.getText(),txtEmail.getText(),txtTelNo.getText(),txtState.getText(),txtAddress.getText())
                )){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again :(").show();
                }
            } catch (SQLException throwables) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
                return;
            }
        }
        try {
            loadGuests("");
            addNew(actionEvent);
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
            return;
        }

    }

    public void addNew(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnSubmit.setText("Register");
        lblTopic.setText("New Registration");
        txtId.clear();
        txtName.clear();
        txtNic.clear();
        txtEmail.clear();
        txtTelNo.clear();
        txtState.clear();
        txtAddress.clear();
        setNextId();
    }
}
