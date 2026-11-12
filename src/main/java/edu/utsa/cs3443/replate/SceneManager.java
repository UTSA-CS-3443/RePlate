package edu.utsa.cs3443.replate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private final Stage stage;
    private FXMLLoader loader;

    // Stores previously loaded scenes
    private final Map<String, Scene> scenes = new HashMap<>();

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    // Load and cache a scene
    public void addScene(String name, String fxmlPath) {
        try {
            loader = new FXMLLoader(getClass().getResource(fxmlPath));

            Parent root = loader.load();

            // Retrieve the FXML controller
            Object controller = loader.getController();

            // Give controller access to SceneManager
            if (controller instanceof SceneAware aware) {
                aware.setSceneManager(this);
            }
            scenes.put(name, new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Switch to a cached scene
    public void switchScene(String name) {

        Scene scene = scenes.get(name);

        if (scene != null) {
            stage.setScene(scene);
        } else {
            System.out.println(scenes);
            System.out.println("Scene not found: " + name);
        }
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }

    public Map<String, Scene> getMap() {
        return scenes;
    }

    public Stage getStage() {
        return stage;
    }
}
