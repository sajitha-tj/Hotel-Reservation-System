package controller;

import bo.BoFactory;
import bo.custom.BillDataBO;
import bo.custom.GuestBO;
import bo.custom.RoomBO;
import dto.BillDataDTO;
import dto.GuestDTO;
import dto.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CheckOutTM;
import view.tm.GuestHistoryTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestHistoryFormController {
    public TextField txtSearch;
    public TableView<GuestHistoryTM> tblInfo;
    public TableColumn colBillId;
    public TableColumn colRoomNo;
    public TableColumn colRoomType;
    public TableColumn colGuestId;
    public TableColumn colName;
    public TableColumn colNicNo;
    public TableColumn colTelNo;
    public TableColumn colNoOfPersons;
    public TableColumn colInDate;
    public TableColumn colOutDate;
    public TableColumn colNoOfDays;
    public TableColumn colTotal;
    public TableColumn colPaid;
    public TableColumn colBalance;
    public TableColumn colStatus;

    BillDataBO billDataBO = BoFactory.getInstance().getBo(BoFactory.BoType.BILL_DATA);
    GuestBO guestBO = BoFactory.getInstance().getBo(BoFactory.BoType.GUEST);
    RoomBO roomBO = BoFactory.getInstance().getBo(BoFactory.BoType.ROOM);

    public void initialize() throws SQLException, ClassNotFoundException {
        colBillId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNicNo.setCellValueFactory(new PropertyValueFactory<>("nicNo"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colNoOfPersons.setCellValueFactory(new PropertyValueFactory<>("noOfPersons"));
        colInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colNoOfDays.setCellValueFactory(new PropertyValueFactory<>("noOfDays"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colPaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTables("");

        //search--
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadTables(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadTables(String text) throws SQLException, ClassNotFoundException {
        ArrayList<BillDataDTO> allBills = billDataBO.search(text);
        ObservableList<GuestHistoryTM> obList = FXCollections.observableArrayList();
        for (BillDataDTO dto:allBills){

            ArrayList<RoomDTO> listForRoomType = roomBO.searchRoom(dto.getRoomNo());
            RoomDTO roomDets = listForRoomType.get(0);

            ArrayList<GuestDTO> listForGuestDets = guestBO.searchGuests(dto.getGuestId());
            GuestDTO guestDets = listForGuestDets.get(0);

            obList.addAll(new GuestHistoryTM(
                    dto.getBillId(),dto.getRoomNo(),roomDets.getRoomType(),dto.getGuestId(), guestDets.getName(), guestDets.getNicNo(), guestDets.getTelNo(),Integer.sum(dto.getNoOfAdults(),dto.getNoOfChildren()), dto.getCheckInDate(),dto.getCheckOutDate(),dto.getNoOfDays(), dto.getTotal(), dto.getAmountPaid(), dto.getBalance(), dto.getStatus()
            ));
        }
        tblInfo.setItems(obList);
    }
}
