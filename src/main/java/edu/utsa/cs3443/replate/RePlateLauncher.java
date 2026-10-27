package edu.utsa.cs3443.replate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RePlateLauncher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RePlateLauncher.class.getResource("dashboard.fxml"));
//        try {
            Scene dashboard = new Scene(fxmlLoader.load());
            stage.setTitle("RePlate");
            stage.setScene(dashboard);
            stage.show();
//        } catch (Exception e) {
//            System.out.println("Could not load dashboard fxml");
//        }
    }
}
