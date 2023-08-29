package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane cashieranchorpane;

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private PasswordField txtLogPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button btnShowPassword;

    @FXML
    private TextField txtPassword2;

    @FXML
    void dontHaveAccountOnActon(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/new_account_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
    }

    @FXML
    void loginPageOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        //stage.setTitle("SPICY FLAVOUR");
        //stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        root = FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        adminAncPane.getScene().getWindow().hide();

    }

    @FXML
    void initialize() {
        txtPassword2.setVisible(false);
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
    void ShowPasswordOnAction(ActionEvent event) {}
    @FXML
    void forgotYourPasswordOnAction(ActionEvent event) {}

}
