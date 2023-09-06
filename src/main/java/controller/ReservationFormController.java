package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDto;
import dto.RoomDto;
import dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ServiceFactory;
import service.custom.ReservationService;
import view.tm.ReservationTM;

import java.time.LocalDate;
import java.util.List;

public class ReservationFormController {

    @FXML
    private TableView<ReservationTM> mainCOMStudent;

    @FXML
    private TableColumn<?, ?> COMResId;

    @FXML
    private TableColumn<?, ?> COMResDate;

    @FXML
    private TableColumn<?, ?> COMStuId;

    @FXML
    private TableColumn<?, ?> COMRoomType;

    @FXML
    private TableColumn<?, ?> COMStatus;

    @FXML
    private DatePicker dpresDate;

    @FXML
    private JFXComboBox COBRoomId;

    @FXML
    private JFXTextField txtResId;

    @FXML
    private JFXComboBox COBStatus;

    @FXML
    private ComboBox<String> COBStudentId;

    @FXML
    private Label lblReid;

    ReservationService reservationService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.RESERVATION);

    @FXML
    void deleteOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            ReservationDto reservationDto = getResovation();
            boolean deleteRes = reservationService.deleteReservation(reservationDto);
            if (deleteRes) {
                AlertController.okconfirmmessage("deleted");
                setDataToTableView();
                clearAll();
            } else {
                AlertController.animationMesseagewrong("Error","Error!");
            }
        }
    }

    private void clearAll() {
        txtResId.setText("");
        dpresDate.setValue(null);
        COBStudentId.setValue("");
        COBRoomId.setValue("");
        COBStatus.setValue("");
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            if (ValidateField.ResevationIdCheck(txtResId.getText())) {
                lblReid.setVisible(false);
                ReservationDto reservationDto = getResovation();
                String reservation = reservationService.saveReservation(reservationDto);
                if (reservation.equals(txtResId.getText())) {
                    AlertController.okconfirmmessage("save succsess!");
                    setDataToTableView();
                    clearAll();
                } else {
                    AlertController.animationMesseagewrong("Error","Error!");
                }
            } else {
                lblReid.setVisible(true);
            }
        }else{
            AlertController.animationMesseagewrong("Error","please make sure to fill out all the required fields");
        }
    }

    private ReservationDto getResovation() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setResId(txtResId.getText());
        reservationDto.setDate(dpresDate.getValue()); // <==== Add
        reservationDto.setStudentId(String.valueOf(COBStudentId.getValue()));
        reservationDto.setRoomTypeId(String.valueOf(COBRoomId.getValue()));
        reservationDto.setStatus(String.valueOf(COBStatus.getValue()));

        return reservationDto;
    }

    @FXML
    void studentOnMouse(MouseEvent event) {
        TablePosition pos = mainCOMStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<ReservationTM, ?>> columns = mainCOMStudent.getColumns();
        txtResId.setText(columns.get(0).getCellData(row).toString());
        dpresDate.setValue((LocalDate) columns.get(1).getCellData(row));
        COBStudentId.setValue(columns.get(2).getCellData(row).toString());
        COBRoomId.setValue(columns.get(3).getCellData(row).toString());
        COBStatus.setValue(columns.get(4).getCellData(row).toString());
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            if (ValidateField.ResevationIdCheck(txtResId.getText())) {
                lblReid.setVisible(false);
                ReservationDto reservationDto = getResovation();
                boolean savedCusId = reservationService.updateReservation(reservationDto);
                if (savedCusId) {
                    AlertController.okconfirmmessage("update succsess!");
                    setDataToTableView();
                    clearAll();
                } else {
                    AlertController.animationMesseagewrong("Error","Error!");
                }
            } else {
                lblReid.setVisible(true);
            }
        }else{
            AlertController.animationMesseagewrong("Error","please make sure to fill out all the required fields");
        }
    }

    public void getAllRoomId() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = reservationService.loadRoomIds();
            for (String id : ids) {
                obList.add(id);
            }
            COBRoomId.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getAllStudentId() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = reservationService.loadStudentIds();
            for (String id : ids) {
                obList.add(id);
            }
            COBStudentId.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean noEmptyValuesInTextFields() {
        LocalDate date = dpresDate.getValue();
        String roomId = String.valueOf(COBRoomId.getValue());
        String status = String.valueOf(COBStatus.getValue());

        if (!txtResId.getText().isEmpty() && date!=null && !roomId.isEmpty() && roomId!=null && status!=null) {
            return true;
        } else {
            return false;
        }
    }

    public void setDataToTableView() {
        ObservableList<ReservationDto> reservationDtos = reservationService.getDetailsToTableView();
        ObservableList<ReservationTM> reservationTM = FXCollections.observableArrayList();
        for (ReservationDto rts : reservationDtos){
            reservationTM.add(
                    new ReservationTM(
                            rts.getResId(),
                            rts.getDate(),
                            rts.getStudentId(),
                            rts.getRoomTypeId(),
                            rts.getStatus()
                    )
            );
        }
        mainCOMStudent.setItems(reservationTM);
    }

    public void setCellValueFactory() {
        COMResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        COMResDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        COMStuId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        COMRoomType.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        COMStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    void initialize() {
        setDataToTableView();
        COBStatus.getItems().addAll("Paid", "not Paid");
        getAllRoomId();
        getAllStudentId();
        setCellValueFactory();
        lblReid.setVisible(false);
    }


}
