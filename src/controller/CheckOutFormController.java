package controller;

import bo.BoFactory;
import bo.custom.BillDataBO;
import bo.custom.GuestBO;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dao.custom.BillDataDao;
import dto.BillDataDTO;
import dto.GuestDTO;
import dto.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CheckOutTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CheckOutFormController {
    public JFXTextField txtRoomNo;
    public JFXTextField txtRoomType;
    public JFXTextField txtNoOfAdults;
    public JFXTextField txtNoOfChildren;
    public JFXDatePicker dtpInDate;
    public JFXDatePicker dtpOutDate;
    public JFXTextField txtNoOfDays;
    public JFXTextField txtId;
    public JFXTextField txtNic;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtTelNo;
    public JFXTextField txtAddress;
    public JFXTextField txtState;
    public TextField txtSearch;
    public TableView<CheckOutTM> tblInfo;
    public TableColumn colBillId;
    public TableColumn colRoomNo;
    public TableColumn colRoomType;
    public TableColumn colGuestId;
    public TableColumn colName;
    public TableColumn colNicNo;
    public TableColumn colTelNo;
    public TableColumn colInDate;
    public TableColumn colNoOfAdults;
    public TableColumn colNoOfKids;
    public TableColumn colNoOfDays;
    public JFXTextField txtPrice;
    public JFXTextField txtTotalCharge;
    public JFXTextField txtOtherCharges;
    public JFXTextField txtSubTotal;
    public JFXTextField txtDiscount;
    public JFXTextField txtTotal;
    public JFXTextField txtAmountPaid;
    public JFXTextField txtBalance;
    public JFXTextField txtBillId;


    BillDataBO billDataBO = BoFactory.getInstance().getBo(BoFactory.BoType.BILL_DATA);
    GuestBO guestBO = BoFactory.getInstance().getBo(BoFactory.BoType.GUEST);
    RoomBO roomBO = BoFactory.getInstance().getBo(BoFactory.BoType.ROOM);

    public void initialize() throws SQLException, ClassNotFoundException {
        dtpOutDate.setValue(LocalDate.now());

        colBillId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNicNo.setCellValueFactory(new PropertyValueFactory<>("nicNo"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colNoOfAdults.setCellValueFactory(new PropertyValueFactory<>("noOfAdults"));
        colNoOfKids.setCellValueFactory(new PropertyValueFactory<>("noOfChildren"));
        colNoOfDays.setCellValueFactory(new PropertyValueFactory<>("noOfDays"));

        loadTable("");

        //search--
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadTable(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        //select--
        tblInfo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                try {
                    setTblData(newValue);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        //subTotal--
        txtOtherCharges.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!txtOtherCharges.getText().isEmpty()) {
                calculateSubTotal(newValue);
            }
        }));

        //Total--
        txtDiscount.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!txtDiscount.getText().isEmpty()) {
                calculateTotal(newValue);
            }
        }));

        //Balance--
        txtAmountPaid.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!txtAmountPaid.getText().isEmpty()) {
                calculateBalance(newValue);
            }
        }));
    }

    private void calculateBalance(String newValue) {
        txtBalance.setText(
                String.valueOf(Double.parseDouble(txtAmountPaid.getText())-Double.parseDouble(txtTotal.getText()))
        );
    }

    private void calculateTotal(String newValue) {
        txtTotal.setText(
                String.valueOf(((100 - (Double.parseDouble(txtDiscount.getText()))) * Double.parseDouble(txtSubTotal.getText())) / 100)
        );
    }

    private void calculateSubTotal(String newValue) {
        txtSubTotal.setText(
                String.valueOf(Double.parseDouble(txtTotalCharge.getText())+Double.parseDouble(txtOtherCharges.getText()))
        );
    }

    private void setTblData(CheckOutTM tm) throws SQLException, ClassNotFoundException {
        ArrayList<RoomDTO> listForRoomType = roomBO.searchRoom(tm.getRoomNo());
        RoomDTO roomDets = listForRoomType.get(0);

        ArrayList<GuestDTO> listForGuestDets = guestBO.searchGuests(tm.getGuestId());
        GuestDTO guestDets = listForGuestDets.get(0);

        txtRoomNo.setText(tm.getRoomNo());
        txtRoomType.setText(roomDets.getRoomType());
        txtNoOfAdults.setText(String.valueOf(tm.getNoOfAdults()));
        txtNoOfChildren.setText(String.valueOf(tm.getNoOfChildren()));
        dtpInDate.setValue(tm.getCheckInDate());
        dtpOutDate.setValue(LocalDate.now());
        txtNoOfDays.setText(String.valueOf(dtpInDate.getValue().until(dtpOutDate.getValue(), ChronoUnit.DAYS)));
        if(dtpInDate.getValue().until(dtpOutDate.getValue(), ChronoUnit.DAYS) == 0){
            txtNoOfDays.setText("1");
        }
        txtId.setText(guestDets.getGuestId());
        txtNic.setText(guestDets.getNicNo());
        txtName.setText(guestDets.getName());
        txtEmail.setText(guestDets.getEmail());
        txtTelNo.setText(guestDets.getTelNo());
        txtAddress.setText(guestDets.getAddress());
        txtState.setText(guestDets.getState());
        txtPrice.setText(roomDets.getPrice());
        txtTotalCharge.setText(
                String.valueOf((Double.parseDouble(txtNoOfDays.getText())*Double.parseDouble(txtPrice.getText())))
        );
        txtBillId.setText(String.valueOf(tm.getBillId()));

    }

    private void loadTable(String txt) throws SQLException, ClassNotFoundException {
        ArrayList<BillDataDTO> allBills = billDataBO.searchCheckedInRooms(txt);
        ObservableList<CheckOutTM> obList = FXCollections.observableArrayList();
        for (BillDataDTO dto:allBills){

            ArrayList<RoomDTO> listForRoomType = roomBO.searchRoom(dto.getRoomNo());
            String roomType = listForRoomType.get(0).getRoomType();

            ArrayList<GuestDTO> listForGuestDets = guestBO.searchGuests(dto.getGuestId());
            GuestDTO guestDets = listForGuestDets.get(0);

            obList.addAll(new CheckOutTM(
                    dto.getBillId(),dto.getRoomNo(),roomType,dto.getGuestId(), guestDets.getName(), guestDets.getNicNo(), guestDets.getTelNo(), dto.getCheckInDate(),dto.getNoOfAdults(),dto.getNoOfChildren(),dto.getNoOfDays()
            ));
        }
        tblInfo.setItems(obList);
    }

    public void onSubmit(ActionEvent actionEvent) throws ClassNotFoundException {

        //-------Validations----
        if(txtId.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please select a customer from the Table").show();
            return;
        }
        if(txtOtherCharges.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Fill other charges Field").show();
            return;
        }
        if(txtDiscount.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Fill the discount Field").show();
            return;
        }
        if(txtAmountPaid.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Fill the Amount paid Field").show();
            return;
        }
        //-------
        try {
            if(billDataBO.updateOnCheckOut(
                    new BillDataDTO(
                            Integer.parseInt(txtBillId.getText()),txtId.getText(),txtName.getText(),txtRoomNo.getText(),Integer.parseInt(txtNoOfAdults.getText()),Integer.parseInt(txtNoOfChildren.getText()),dtpInDate.getValue(),dtpOutDate.getValue(),Integer.parseInt(txtNoOfDays.getText()),Double.parseDouble(txtTotalCharge.getText()),Double.parseDouble(txtOtherCharges.getText()),Double.parseDouble(txtSubTotal.getText()),Double.parseDouble(txtDiscount.getText()),Double.parseDouble(txtTotal.getText()),Double.valueOf(txtAmountPaid.getText()),Double.valueOf(txtBalance.getText()),"Bill-Closed")
                    )
                && roomBO.updateAvailabilityOnCheckOut(txtRoomNo.getText())
            ){
                new Alert(Alert.AlertType.CONFIRMATION,"Bill Submitted Successfully").show();
                loadTable("");
                clearData();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try again :(").show();
            }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
        }
    }

    private void clearData() {
        txtRoomNo.clear();
        txtRoomType.clear();
        txtNoOfAdults.clear();
        txtNoOfChildren.clear();
        dtpInDate.getEditor().clear();
        txtNoOfDays.clear();
        txtId.clear();
        txtNic.clear();
        txtName.clear();
        txtEmail.clear();
        txtTelNo.clear();
        txtAddress.clear();
        txtState.clear();
        txtPrice.clear();
        txtTotalCharge.clear();
        txtOtherCharges.clear();
        txtSubTotal.clear();
        txtDiscount.clear();
        txtTotal.clear();
        txtAmountPaid.clear();
        txtBalance.clear();
        txtBillId.clear();
    }
}
