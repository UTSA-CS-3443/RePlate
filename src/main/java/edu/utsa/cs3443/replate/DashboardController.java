package edu.utsa.cs3443.replate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DashboardController implements SceneAware {
    private SceneManager sceneManager;
    private Stage stage;


    public DashboardController() {
        System.out.println("DashboardController instantiated!");
    }

    @FXML
    private Label label_header_date;

    @FXML
    private Label label_header_sub;

    @FXML
    private Button button_add;

    @FXML
    private Label dashboard_detail_header;

    @FXML
    private VBox dashboard_detail;


    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void onPressButtonAdd() {
        sceneManager.switchScene("add-food");
    }


}
