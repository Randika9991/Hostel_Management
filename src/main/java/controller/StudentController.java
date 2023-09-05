package controller;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ServiceFactory;
import service.custom.StudentService;
import view.tm.StudentTM;

import java.time.LocalDate;

public class StudentController {

    @FXML
    private JFXTextField txtStuId;

    @FXML
    private JFXTextField txtStuName;

    @FXML
    private JFXTextField txtStuAddress;

    @FXML
    private JFXTextField txtStuNumber;

    @FXML
    private TableView<StudentTM> mainCOMStudent;

    @FXML
    private TableColumn<?, ?> COMStuId;

    @FXML
    private TableColumn<?, ?> COMStuName;

    @FXML
    private TableColumn<?, ?> COMStuAddress;

    @FXML
    private TableColumn<?, ?> COMStuContact;

    @FXML
    private TableColumn<?, ?> COMDOB;

    @FXML
    private TableColumn<?, ?> COMGender;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private JFXComboBox<String> COBGender;

    StudentService studentService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.STUDENT);

    @FXML
    void initialize() {
        COBGender.getItems().addAll("male", "female");
        setCellValueFactory();
        setDataToTableView();
    }

    private void clearAll() {
        txtStuId.setText("");
        txtStuName.setText("");
        txtStuAddress.setText("");
        txtStuNumber.setText("");
        dpDateOfBirth.setValue(null);
        COBGender.setValue("");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            StudentDto customer = getStudent();
            boolean savedCusId = studentService.deleteStudent(customer);
            if (savedCusId) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "deleted!").showAndWait();
                setDataToTableView();
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "please make sure to fill out all the required fields").showAndWait();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            StudentDto customer = getStudent();
            String savedCusId = studentService.saveStudent(customer);
            if (!savedCusId.equals(null)) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "save succsess!").showAndWait();
                setDataToTableView();
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "please make sure to fill out all the required fields").showAndWait();
        }
    }

    @FXML
    void studentOnMouse(MouseEvent event) {
        TablePosition pos = mainCOMStudent.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ObservableList<TableColumn<StudentTM, ?>> columns = mainCOMStudent.getColumns();

        txtStuId.setText(columns.get(0).getCellData(row).toString());
        txtStuName.setText(columns.get(1).getCellData(row).toString());
        txtStuAddress.setText(columns.get(2).getCellData(row).toString());
        txtStuNumber.setText(columns.get(3).getCellData(row).toString());
        dpDateOfBirth.setValue((LocalDate) columns.get(4).getCellData(row));
        COBGender.setValue(columns.get(5).getCellData(row).toString());
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        boolean emptyFields =  noEmptyValuesInTextFields() ;
        if (emptyFields) {
            StudentDto customer = getStudent();
            boolean savedCusId = studentService.updateStudent(customer);
            if (savedCusId) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "update succsess!").showAndWait();
                setDataToTableView();
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "please make sure to fill out all the required fields").showAndWait();
        }
    }

    public boolean noEmptyValuesInTextFields() {
        String stId = txtStuId.getText();
        String stName = txtStuName.getText();
        String address = txtStuAddress.getText();
        String contacNo = txtStuNumber.getText();
        LocalDate date = dpDateOfBirth.getValue();
        String gender = COBGender.getValue();

        if (!stId.isEmpty() && !stName.isEmpty() && !address.isEmpty() && gender!=null && date!=null &&
                !contacNo.isEmpty() && !gender.isEmpty()) {
            return true;
        } else {
            return false;

        }
    }

    private StudentDto getStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(txtStuId.getText());
        studentDto.setName(txtStuName.getText()); // <==== Add
        studentDto.setAddress(txtStuAddress.getText());
        studentDto.setContactNo(txtStuNumber.getText());
        studentDto.setDateOfBirth(dpDateOfBirth.getValue()); // <==== Add
        studentDto.setGender(String.valueOf(COBGender.getValue()));

        return studentDto;
    }

    public void setDataToTableView() {
        ObservableList<StudentDto> studentDtoList = studentService.getDetailsToTableView();
        ObservableList<StudentTM> studentTmList = FXCollections.observableArrayList();
        for (StudentDto dto : studentDtoList){
            studentTmList.add(
                    new StudentTM(
                            dto.getStudentId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getContactNo(),
                            dto.getDateOfBirth(),
                            dto.getGender()
                    )
            );
        }
        mainCOMStudent.setItems(studentTmList);
    }
    public void setCellValueFactory() {
        COMStuId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        COMStuName.setCellValueFactory(new PropertyValueFactory<>("name"));
        COMStuAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        COMStuContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        COMDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        COMGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }
}
