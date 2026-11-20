package edu.utsa.cs3443.replate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardController extends Controller {
//    private Stage stage;

    @FXML
    public void initialize() {
        System.out.println("DashboardController initialized!");
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

//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }

}
