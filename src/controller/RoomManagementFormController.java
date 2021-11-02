package controller;

import bo.BoFactory;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.GuestDTO;
import dto.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.RoomsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomManagementFormController {
    public JFXTextField txtRoomNo;
    public JFXComboBox cmbRoomType;
    public JFXTextField txtPrice;
    public TableView<RoomsTM> tblRooms;
    public TableColumn colRoomNo;
    public TableColumn colRoomType;
    public TableColumn colPrice;
    public TableColumn colAvailability;
    public TableColumn colOptions;
    public TextField txtSearch;
    public JFXButton btnSubmit;
    public Label lblTopic;

    RoomBO bo = BoFactory.getInstance().getBo(BoFactory.BoType.ROOM);

    public void initialize() throws SQLException, ClassNotFoundException {
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colOptions.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadRooms("");
        //select--
        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setRoomData(newValue);
            }
        });
        //search--
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadRooms(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //roomtypes--
        cmbRoomType.getItems().addAll(
                "Normal","Luxury","High-Luxury"
        );
    }

    private void setRoomData(RoomsTM tm){
        btnSubmit.setText("Update");
        lblTopic.setText("Update Info");
        txtRoomNo.setText(tm.getRoomNo());
        cmbRoomType.setValue(tm.getRoomType());
        txtPrice.setText(tm.getPrice());
    }

    private void loadRooms(String txt) throws SQLException, ClassNotFoundException {
        ArrayList<RoomDTO> allRooms = bo.searchRoom(txt);
        ObservableList<RoomsTM> obList = FXCollections.observableArrayList();

        for (RoomDTO dto:allRooms){
            Button btn = new Button("Delete");
            obList.addAll(
                    new RoomsTM(dto.getRoomNo(),dto.getRoomType(),dto.getPrice(),dto.getAvailability(),btn)
            );
            //del
            btn.setOnAction((e->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you wat to delete this?",
                        ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
                alert.showAndWait();

                if(alert.getResult()==ButtonType.YES){
                    try {

                        if(bo.deleteRoom(dto.getRoomNo())){
                            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully").show();
                            loadRooms("");
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
        tblRooms.setItems(obList);
    }

    public void addNewRoom(ActionEvent actionEvent) {
        btnSubmit.setText("Add Room");
        lblTopic.setText("Room Management");
        txtRoomNo.clear();
        cmbRoomType.setValue("");
        txtPrice.clear();
    }

    public void addRoom(ActionEvent actionEvent) throws ClassNotFoundException {

        //------Validations----
        if(cmbRoomType.getValue()==null || txtRoomNo.getText().isEmpty() || txtPrice.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please fill required fields").show();
        }
        //------
        if(btnSubmit.getText().equalsIgnoreCase("Add Room")){
            //register New
            try {
                if(bo.saveRoom(
                        new RoomDTO(txtRoomNo.getText(),cmbRoomType.getValue().toString(),txtPrice.getText(),"Available")
                )){
                    new Alert(Alert.AlertType.CONFIRMATION,"Room Added Successfully").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again :(").show();
                }
            } catch (SQLException throwables) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
                return;
            }
        }else {
            try {
                if(bo.updateRoom(
                        new RoomDTO(txtRoomNo.getText(),cmbRoomType.getValue().toString(),txtPrice.getText(),"Available")
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
            loadRooms("");
            addNewRoom(actionEvent);
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Try Again\n"+"Error:"+throwables.getErrorCode()+"\n"+throwables.getMessage()).show();
            return;
        }
    }
}
