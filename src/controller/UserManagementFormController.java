package controller;

import bo.BoFactory;
import bo.custom.LogInDataBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.LogInDataDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.logInDataTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManagementFormController {
    public TableView<logInDataTM> tblInfo;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colTelNo;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colUserType;
    public TableColumn colStatus;
    public TableColumn colOptions;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtTelNo;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXComboBox cmbUserType;
    public JFXButton btnSubmit;
    public TextField txtSearch;

    LogInDataBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.LOG_IN);

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOptions.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadTable("");

        //search--
        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                loadTable(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));

        //select--
        tblInfo.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null) {
                setData(newValue);
            }else {
                clearData();
            }
        }));

        //userTypes--
        cmbUserType.getItems().addAll(
                "Front-Desk-Officer","Admin"
        );

        //userTypeSelected--
        cmbUserType.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue == null || newValue.toString().equalsIgnoreCase("not-Defined")){
                btnSubmit.setDisable(true);
            }else {
                btnSubmit.setDisable(false);
            }
        }));

    }

    private void setData(logInDataTM tm) {
        txtId.setText(String.valueOf(tm.getUserId()));
        txtName.setText(tm.getName());
        txtTelNo.setText(tm.getTelNo());
        txtAddress.setText(tm.getAddress());
        txtEmail.setText(tm.getEmail());
        cmbUserType.setValue(tm.getUserType());
    }

    private void clearData() {
        txtId.clear();
        txtName.clear();
        txtTelNo.clear();
        txtAddress.clear();
        txtEmail.clear();
        cmbUserType.getEditor().clear();
    }

    private void loadTable(String text) throws SQLException, ClassNotFoundException {
        ArrayList<LogInDataDTO> dtoList = bo.search(text);
        ObservableList<logInDataTM> obList = FXCollections.observableArrayList();
        for (LogInDataDTO dto :dtoList){
            Button btn = new Button("Delete");
            obList.addAll(new logInDataTM(
                    dto.getUserId(), dto.getName(), dto.getTelNo(), dto.getAddress(), dto.getEmail(), dto.getUserType(), dto.getStatus(), btn
            ));

            //delete btn--
            btn.setOnAction((e->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you wat to delete this user?",
                        ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
                alert.showAndWait();

                if(alert.getResult()==ButtonType.YES){
                    try {

                        if(bo.deleteUser(dto.getUserId())){
                            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully").show();
                            loadTable("");
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
        tblInfo.setItems(obList);
    }

    public void onSubmit(ActionEvent actionEvent) {
        try {
            if(bo.updateUser(new LogInDataDTO(
                    Integer.parseInt(txtId.getText()),txtName.getText(),txtTelNo.getText(),txtAddress.getText(),txtEmail.getText(),cmbUserType.getValue().toString(),"Allowed",0
            ))){
                new Alert(Alert.AlertType.CONFIRMATION, "User assigned Successfully").show();
                loadTable("");
            }
            else {
                new Alert(Alert.AlertType.WARNING, "Please try again :(").show();
            }
        } catch (Exception throwables) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong.\nError:\n"+throwables.getMessage()).show();
        }
    }
}
