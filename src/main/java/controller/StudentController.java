package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDto;
import entity.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import repositrory.impl.StudentRepositoryimpl;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.time.LocalDate;
import java.util.List;

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
    private TableView<?> mainCOMStudent;

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
    private JFXComboBox COBStudentId;

    @FXML
    void initialize() {
        COBStudentId.getItems().addAll("male", "female");


        /*System.out.println("------JPQL Get All-----------");
        StudentRepositoryimpl studentRepositoryimpl = new StudentRepositoryimpl();
        List<Student> allStudentNative = studentRepositoryimpl.getAllStudentJPQL();
        for (Student student : allStudentNative) {
            System.out.println(student);
        }*/
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        StudentDto customer = getStudent();
        StudentService studentService = StudentServiceImpl.getInstance();
        boolean savedCusId = studentService.deleteStudent(customer);
        System.out.println("Saved Cus Id: " + savedCusId);
        if (savedCusId) {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "deleted!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        StudentDto customer = getStudent();
        StudentService studentService = StudentServiceImpl.getInstance();
        int savedCusId = studentService.saveStudent(customer);
        System.out.println("Saved Cus Id: " + savedCusId);
        if (savedCusId == 1) {
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
        StudentDto customer = getStudent();
        StudentService studentService = StudentServiceImpl.getInstance();
        boolean savedCusId = studentService.updateStudent(customer);
        System.out.println("Saved Cus Id: " + savedCusId);
        if (savedCusId) {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "update succsess!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
        }

    }
    private StudentDto getStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(txtStuId.getText());
        studentDto.setName(txtStuName.getText()); // <==== Add
        studentDto.setAddress(txtStuAddress.getText());
        studentDto.setContactNo(txtStuNumber.getText());
        studentDto.setDateOfBirth(dpDateOfBirth.getValue()); // <==== Add
        studentDto.setGender(String.valueOf(COBStudentId.getValue()));


//        NameIdentifier nameIdentifier = getNameIdentifier();
//        customer.setNameIdentifier(nameIdentifier);

        //studentDto.setStudentId("001");
        /*studentDto.setName("Saman"); // <==== Add
        studentDto.setAddress("Galle");
        studentDto.setContactNo("0770734566");
        studentDto.setDateOfBirth(LocalDate.parse("2023-08-30")); // <==== Add
        studentDto.setGender("Male");
*/
//        List<MobileNo> mobileNos = getMobileNos();
//        customer.setPhoneNos(mobileNos);
        return studentDto;
    }




}
