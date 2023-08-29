package controller;

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
    void studentOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);

    }



}
