import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Demo extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
       // Session session = SessionFactoryConfig.getInstance().getSession();
        URL resource = Demo.class.getResource("view/login_form.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        // stage.getIcons().add(new Image("lk.ijse.orm.assets/icons8-chilli-100.png"));
        stage.setTitle("HOSTEL MANAGEMENT");
        stage.centerOnScreen();
        stage.show();
    }
}
