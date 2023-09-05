package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
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

    RoomService roomService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.ROOM);

    @FXML
    void initialize() {
        setDataToTableView();
        setCellValueFactory();
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            System.out.println(txtRoomTyId.getText());
            RoomDto roomDto = getStudent();
            boolean savedCusId = roomService.deleteRoom(roomDto);
            System.out.println("Saved Cus Id: " + savedCusId);
            if (savedCusId) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "deleted!").showAndWait();
                setDataToTableView();
            } else {
                new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "please make sure to fill out all the required fields").showAndWait();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            RoomDto roomDto = getStudent();
            String savedCusId = roomService.saveRoom(roomDto);
            System.out.println("Saved Cus Id: " + savedCusId);
            if (savedCusId.equals(txtRoomTyId.getText())) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "save succsess!").showAndWait();
                setDataToTableView();
            } else {
                new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "please make sure to fill out all the required fields").showAndWait();
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
            RoomDto roomDto = getStudent();
            boolean savedCusId = roomService.updateRoom(roomDto);
            System.out.println("Saved Cus Id: " + savedCusId);
            if (savedCusId) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "update succsess!").showAndWait();
                setDataToTableView();
            } else {
                new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "please make sure to fill out all the required fields").showAndWait();
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
