package edu.utsa.cs3443.replate;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RePlateLauncher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            SceneManager sceneManager = new SceneManager(stage);

            sceneManager.addScene("dashboard", "dashboard.fxml");
            sceneManager.addScene("add-food", "add-food.fxml");
//            sceneManager.addScene("food-pantry", "food-pantry.fxml");
//            sceneManager.addScene("expired-food", "expired-food.fxml");
            sceneManager.switchScene("dashboard");

            System.out.println(sceneManager.getMap());

            stage.setTitle("RePlate");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}