package controller;

import com.jfoenix.controls.JFXTextField;
import dto.CreateNewUserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import service.ServiceFactory;
import service.custom.SettingService;
import service.custom.impl.SettingServiceImpl;

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
    void doneOnAction(ActionEvent event) {
        CreateNewUserDto student = getStudent();

        boolean savedCusId = settingService.updateUser(student);
        if (savedCusId) {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "update succsess!").showAndWait();
            lableUser.setVisible(true);
            lablePassword.setVisible(true);
            txtStuName.setVisible(false);
            txtStuPassword.setVisible(false);
        } else {
            new Alert(Alert.AlertType.ERROR, "Erorr!").showAndWait();
        }
    }

    private CreateNewUserDto getStudent() {
        CreateNewUserDto createNewUserDto = new CreateNewUserDto();
        createNewUserDto.setUserName(txtStuName.getText()); // <==== Add
        createNewUserDto.setUserPassword(txtStuPassword.getText());
        return createNewUserDto;
    }

    @FXML
    void initialize() {
        txtStuName.setVisible(false);
        txtStuPassword.setVisible(false);
    }
}
