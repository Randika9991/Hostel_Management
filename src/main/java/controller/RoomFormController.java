package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ServiceFactory;
import service.custom.RoomService;
import view.tm.RoomTM;
import view.tm.StudentTM;

import java.time.LocalDate;

public class RoomFormController {

    @FXML
    private JFXTextField txtRoomTyId;

    @FXML
    private JFXTextField txtRoomType;

    @FXML
    private JFXTextField txtKeyMoney;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    private TableView<RoomTM> mainCOMStudent;

    @FXML
    private TableColumn<?, ?> COMRoomTYId;

    @FXML
    private TableColumn<?, ?> COMRoomType;

    @FXML
    private TableColumn<?, ?> COMKeMoney;

    @FXML
    private TableColumn<?, ?> COMTotalRoom;

    @FXML
    private TableColumn<?, ?> COMMax;

    @FXML
    private TableColumn<?, ?> COMAvailable;

    @FXML
    private JFXTextField txtMaxNo;

    @FXML
    private Label lblRMSTID;

    RoomService roomService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.ROOM);

    @FXML
    void initialize() {
        setDataToTableView();
        setCellValueFactory();
        lblRMSTID.setVisible(false);
    }

    private void clearAll() {
        txtRoomTyId.setText("");
        txtRoomType.setText("");
        txtKeyMoney.setText("");
        txtQTY.setText("");
        txtMaxNo.setText("");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            System.out.println(txtRoomTyId.getText());
            RoomDto roomDto = getStudent();
            boolean savedCusId = roomService.deleteRoom(roomDto);
            if (savedCusId) {
                AlertController.okconfirmmessage("deleted");
                setDataToTableView();
                clearAll();
            } else {
                AlertController.animationMesseagewrong("Error","Error!");
            }
        }else{
            AlertController.animationMesseagewrong("Error","please make sure to fill out all the required fields");
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            if (ValidateField.roomIdCheck(txtRoomTyId.getText())) {
                lblRMSTID.setVisible(false);
                RoomDto roomDto = getStudent();
                String savedCusId = roomService.saveRoom(roomDto);
                if (savedCusId.equals(txtRoomTyId.getText())) {
                    AlertController.okconfirmmessage("save succsess!");
                    setDataToTableView();
                    clearAll();
                } else {
                    AlertController.animationMesseagewrong("Error","Error!");
                }
            } else {
                lblRMSTID.setVisible(true);
            }
        }else{
            AlertController.animationMesseagewrong("Error","please make sure to fill out all the required fields");
        }
    }

    @FXML
    void roomOnMouse(MouseEvent event) {
        TablePosition pos = mainCOMStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<RoomTM, ?>> columns = mainCOMStudent.getColumns();

        txtRoomTyId.setText(columns.get(0).getCellData(row).toString());
        txtRoomType.setText(columns.get(1).getCellData(row).toString());
        txtKeyMoney.setText(columns.get(2).getCellData(row).toString());
        txtQTY.setText(columns.get(3).getCellData(row).toString());
        txtMaxNo.setText(columns.get(4).getCellData(row).toString());
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            if (ValidateField.roomIdCheck(txtRoomTyId.getText())) {
                lblRMSTID.setVisible(false);
                RoomDto roomDto = getStudent();
                boolean savedCusId = roomService.updateRoom(roomDto);
                if (savedCusId) {
                    AlertController.okconfirmmessage("update succsess!");
                    setDataToTableView();
                    clearAll();
                } else {
                    AlertController.animationMesseagewrong("Error","Error!");
                }
            }else {
                lblRMSTID.setVisible(true);
            }
        }else{
            AlertController.animationMesseagewrong("Error","please make sure to fill out all the required fields");
        }
    }

    public void setDataToTableView() {
        ObservableList<RoomDto> roomDtoList = roomService.getDetailsToTableView();
        ObservableList<RoomTM> roomTmList = FXCollections.observableArrayList();
        for (RoomDto dto : roomDtoList){
            roomTmList.add(
                    new RoomTM(
                            dto.getRoomTypeId(),
                            dto.getType(),
                            dto.getKeyMoney(),
                            dto.getQty(),
                            dto.getAvailableRooms(),
                            dto.getPerRoom()
                    )
            );
        }
        mainCOMStudent.setItems(roomTmList);
    }

    public boolean noEmptyValuesInTextFields() {
        String id = txtRoomTyId.getText();
        String type = txtRoomType.getText();
        String money = txtKeyMoney.getText();
        //int qty = Integer.parseInt(txtQTY.getText());
       // int max = Integer.parseInt(txtMaxNo.getText());

        if (!id.isEmpty() && !type.isEmpty() && !money.isEmpty()&& !txtQTY.getText().isEmpty() && !txtMaxNo.getText().isEmpty()) {
            return true;
        } else {
            return false;

        }
    }

    public void setCellValueFactory() {
        COMRoomTYId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        COMRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        COMKeMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        COMTotalRoom.setCellValueFactory(new PropertyValueFactory<>("qty"));
        COMAvailable.setCellValueFactory(new PropertyValueFactory<>("availableRooms"));
        COMMax.setCellValueFactory(new PropertyValueFactory<>("perRoom"));
    }

    private RoomDto getStudent() {
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomTypeId(txtRoomTyId.getText());
        roomDto.setType(txtRoomType.getText()); // <==== Add
        roomDto.setKeyMoney(txtKeyMoney.getText());
        roomDto.setQty(Integer.parseInt(txtQTY.getText()));
        roomDto.setPerRoom(Integer.parseInt(txtMaxNo.getText()));

        return roomDto;
    }

}
