package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane cashieranchorpane;

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private AnchorPane smallAnchorPane;

    @FXML
    private JFXButton btnSetting;

    @FXML
    void studentOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/setting_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
    }

    @FXML
    void reservationsOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/Reservation_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
    }

    @FXML
    void roomOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/room_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
    }
}
