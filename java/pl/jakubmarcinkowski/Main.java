package pl.jakubmarcinkowski;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
       Parent parent = FXMLLoader.load(getClass().getResource("/view/first.fxml"));

        Scene scene = new Scene(parent, 1024, 768);
        stage.setScene(scene);

        stage.show();
    }
}