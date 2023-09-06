package controller;

import com.jfoenix.controls.JFXTextField;
import dto.CreateNewUserDto;
import dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import service.ServiceFactory;
import service.custom.LoginService;
import service.custom.SettingService;
import service.custom.impl.SettingServiceImpl;
import view.tm.StudentTM;

import java.awt.*;

public class SettingFormController {

    @FXML
    private JFXTextField txtStuName;

    @FXML
    private JFXTextField txtStuPassword;

    @FXML
    private Label lableUser;

    @FXML
    private Label lablePassword;

    SettingService settingService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.SETTING);
    @FXML
    void changeOnActionUser(ActionEvent event) {
        lableUser.setVisible(false);
        txtStuName.setVisible(true);
    }

    @FXML
    void changeOnActionPassword(ActionEvent event) {
        lablePassword.setVisible(false);
        txtStuPassword.setVisible(true);
    }

    @FXML
    void doneOnAction(ActionEvent event) throws AWTException {
        CreateNewUserDto student = getStudent();

        boolean savedCusId = settingService.updateUser(student);
        if (savedCusId) {
            AlertController.notificationBar("HOSTEL MANAGEMENT","Password Change success!");

            setDataToTableView();
            lableUser.setVisible(true);
            lablePassword.setVisible(true);
            txtStuName.setVisible(false);
            txtStuPassword.setVisible(false);
        } else {
            AlertController.animationMesseagewrong("Error","Error!");
        }
    }

    public void setDataToTableView() {
        LoginFormController loginFormController = new LoginFormController();
        CreateNewUserDto existingCustomer = settingService.getUser(loginFormController.getName());
        System.out.println(existingCustomer);
        lableUser.setText(existingCustomer.getUserName());
        lablePassword.setText(existingCustomer.getUserPassword());
        txtStuName.setText(existingCustomer.getUserName());
    }


    private CreateNewUserDto getStudent() {
        CreateNewUserDto createNewUserDto = new CreateNewUserDto();
        createNewUserDto.setUserName(txtStuName.getText()); // <==== Add
        createNewUserDto.setUserPassword(txtStuPassword.getText());
        return createNewUserDto;
    }

    @FXML
    void initialize() {
        setDataToTableView();
        txtStuName.setVisible(false);
        txtStuPassword.setVisible(false);
    }
}
