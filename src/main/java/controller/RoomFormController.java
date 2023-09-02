package controller;

import com.jfoenix.controls.JFXTextField;
import dto.RoomDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import service.ServiceFactory;
import service.custom.RoomService;
import service.custom.impl.RoomServiceImpl;

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
    private TableView<?> mainCOMStudent;

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
    void deleteOnAction(ActionEvent event) {
        System.out.println(txtRoomTyId.getText());
        RoomDto roomDto = getStudent();
        boolean savedCusId = roomService.deleteRoom(roomDto);
        System.out.println("Saved Cus Id: " + savedCusId);
        if (savedCusId) {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "deleted!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        RoomDto roomDto = getStudent();
        String savedCusId = roomService.saveRoom(roomDto);
        System.out.println("Saved Cus Id: " + savedCusId);
        if (savedCusId.equals(txtRoomTyId.getText())) {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "save succsess!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
        }
    }

    @FXML
    void studentOnMouse(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        RoomDto roomDto = getStudent();
        boolean savedCusId = roomService.updateRoom(roomDto);
        System.out.println("Saved Cus Id: " + savedCusId);
        if (savedCusId) {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "update succsess!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
        }
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
