package controller;

import dto.CreateNewUserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.NewAccountService;

import java.io.IOException;

public class NewAccountFormControoler {

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private TextField txtPassword2;

    @FXML
    private PasswordField txtLogPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button btnShowPassword;

    @FXML
    private Button btnShowConformPassword1;

    @FXML
    private TextField txtpaswordShowConform;

    @FXML
    private PasswordField txtLogConfirmPassword1;

    NewAccountService newAccountService = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.NEW_ACCOUNT);


    @FXML
    void createPageOnAction(ActionEvent event) {
        CreateNewUserDto customer = getStudent();

       // System.out.println("Saved Cus Id: " + savedCusId);
        if (txtLogConfirmPassword1.getText().isEmpty()&&txtUserName.getText().isEmpty()&&txtLogPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill the field!").showAndWait();
        } else {
            if (txtLogConfirmPassword1.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Conform Password!").showAndWait();
            } else {
                if (txtLogPassword.getText().equals(txtLogConfirmPassword1.getText())) {

                    String savedCusId = newAccountService.saveuser(customer);
                    if (!savedCusId.equals(null)) {
                        new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Account Created!").showAndWait();
                    } else {
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong Conform Password!").showAndWait();
                }
            }
        }
    }

    private CreateNewUserDto getStudent() {
        CreateNewUserDto createNewUserDto = new CreateNewUserDto();
       // createNewUserDto.setUserId(1);
        createNewUserDto.setUserName(txtUserName.getText()); // <==== Add
        createNewUserDto.setUserPassword(txtLogPassword.getText());
        return createNewUserDto;
    }

    @FXML
    void loginPageOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        //stage.setTitle("SPICY FLAVOUR");
        //stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        adminAncPane.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        txtPassword2.setVisible(false);
        txtpaswordShowConform.setVisible(false);
    }

    public void OnMousePassword(MouseEvent mouseEvent) {
        txtLogPassword.setVisible(false);
        txtPassword2.setText(txtLogPassword.getText());
        txtPassword2.setVisible(true);
    }

    public void OnMousePWDExt(MouseEvent mouseEvent) {
        txtLogPassword.setVisible(true);
        txtLogPassword.setText(txtPassword2.getText());
        txtPassword2.setVisible(false);
    }

    @FXML
    void OnMousePasswordConform(MouseEvent event) {
        txtLogConfirmPassword1.setVisible(false);
        txtpaswordShowConform.setText(txtLogConfirmPassword1.getText());
        txtpaswordShowConform.setVisible(true);
    }

    @FXML
    void OnMousePWDConformExt(MouseEvent event) {
        txtLogConfirmPassword1.setVisible(true);
        txtLogConfirmPassword1.setText(txtpaswordShowConform.getText());
        txtpaswordShowConform.setVisible(false);
    }

    @FXML
    void ShowConformPasswordOnAction(ActionEvent event) {}

    @FXML
    void ShowPasswordOnAction(ActionEvent event) {}
}
