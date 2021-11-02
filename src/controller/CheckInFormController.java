package controller;

import bo.BoFactory;
import bo.custom.BillDataBO;
import bo.custom.GuestBO;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.BillDataDTO;
import dto.GuestDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import view.tm.GuestsTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class CheckInFormController {
    public TableView<GuestsTM> tblGuests;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colEmail;
    public TableColumn colTelNo;
    public TableColumn colState;
    public TableColumn colAddress;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtTelNo;
    public JFXTextField txtNic;
    public JFXTextField txtState;
    public JFXTextField txtAddress;
    public TextField txtSearch;
    public JFXComboBox cmbRoomType;
    public JFXComboBox cmbRoomNo;
    public JFXTextField txtNoOfAdults;
    public JFXTextField txtNoOfChildren;
    public JFXDatePicker dtpInDate;
    public JFXDatePicker dtpOutDate;
    public JFXTextField txtNoOfDays;

    GuestBO guestBO = BoFactory.getInstance().getBo(BoFactory.BoType.GUEST);
    RoomBO roomBO = BoFactory.getInstance().getBo(BoFactory.BoType.ROOM);
    BillDataBO billDataBO = BoFactory.getInstance().getBo(BoFactory.BoType.BILL_DATA);

    public void initialize() throws SQLException, ClassNotFoundException {
        dtpInDate.setValue(LocalDate.now());

        colId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nicNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadGuests("");

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

        //noOfDays--
        txtNoOfDays.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                dtpOutDate.setValue(dtpInDate.getValue().plusDays(Integer.parseInt(newValue)));
            }else {
                dtpOutDate.getEditor().clear();
            }
        }));

        //roomtypes--
        cmbRoomType.getItems().addAll(
                "Normal","Luxury","High-Luxury"
        );

    }

    private void setGuestData(GuestsTM tm){
        txtId.setText(tm.getGuestId());
        txtName.setText(tm.getName());
        txtNic.setText(tm.getNicNo());
        txtEmail.setText(tm.getEmail());
        txtTelNo.setText(tm.getTelNo());
        txtState.setText(tm.getState());
        txtAddress.setText(tm.getAddress());
    }

    private void loadGuests(String txt) throws SQLException, ClassNotFoundException {
        ArrayList<GuestDTO> allGuests = guestBO.searchGuests(txt);
        ObservableList<GuestsTM> obList = FXCollections.observableArrayList();

        for(GuestDTO dto: allGuests){
            Button btn = new Button("X");
            obList.addAll(
                    new GuestsTM(
                            dto.getGuestId(),dto.getName(), dto.getNicNo(), dto.getEmail(), dto.getTelNo(), dto.getState(), dto.getAddress(), btn
                    )
            );
        }
        tblGuests.setItems(obList);
    }

    public void addNewGuest(ActionEvent actionEvent) {
        /////////////////////////////
    }

    public void assignRooms(ActionEvent actionEvent) throws ClassNotFoundException {

        //-------Validations----
        if(txtId.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please select a customer from the table").show();
            return;
        }
        if(cmbRoomType.getValue() ==null || cmbRoomNo.getValue() == null){
            new Alert(Alert.AlertType.WARNING,"Please select Room type and a room No").show();
            return;
        }
        if(txtNoOfAdults.getText().isEmpty() || txtNoOfChildren.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please enter the No of Adults/Children").show();
            return;
        }
        if(txtNoOfDays.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please enter No of Days or select a check-out date").show();
            return;
        }
        //-------
        try {
            if (billDataBO.saveBill(
                    new BillDataDTO(
                            billDataBO.getNextId(), txtId.getText(), txtName.getText(), cmbRoomNo.getValue().toString(), Integer.parseInt(txtNoOfAdults.getText()), Integer.parseInt(txtNoOfChildren.getText()), dtpInDate.getValue(), dtpOutDate.getValue(), Integer.parseInt(txtNoOfDays.getText()), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "Checked-in"
                    )
            ) && roomBO.updateAvailabilityOnCheckIn(cmbRoomNo.getValue().toString())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room Assigned Successfully").show();
                clearAll();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again :(").show();
            }
        }catch (SQLException throwables){
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
        }
    }

    public void roomTypeSelected(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        cmbRoomNo.getItems().clear();
        if(cmbRoomType.valueProperty().isNotNull().get()){
            cmbRoomNo.getItems().addAll(roomBO.searchRoomNo(cmbRoomType.getValue().toString()));
        }
    }

    public void setNoOfDays(ActionEvent actionEvent) {
        if(dtpOutDate.getEditor().getText().toString()!=""){
            txtNoOfDays.setText(String.valueOf(dtpInDate.getValue().until(dtpOutDate.getValue(), ChronoUnit.DAYS)));
        }

    }

    public void clearAll(){
        txtId.clear();
        txtName.clear();
        txtEmail.clear();
        txtTelNo.clear();
        txtNic.clear();
        txtState.clear();
        txtAddress.clear();
        txtSearch.clear();
        cmbRoomType.valueProperty().set(null);
        cmbRoomNo.valueProperty().set(null);
        txtNoOfAdults.clear();
        txtNoOfChildren.clear();
        dtpOutDate.getEditor().clear();
        txtNoOfDays.clear();
    }
}
