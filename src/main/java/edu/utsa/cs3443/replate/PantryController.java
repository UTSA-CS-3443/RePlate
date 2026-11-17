package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PantryController extends Controller{
    private PantryManager pm;

    public PantryController(){
        System.out.println("PantryController Instantiated");
    }

    @FXML
    public void initialize() {
        pm = PantryManager.getInstance();
        masterPantry = FXCollections.observableArrayList(pm.getFoodPantryList());
    }

    private ObservableList<Food> masterPantry;


    @FXML public void onAllFilterClick() {

    }

    @FXML public void onVeggieFilterClick() {

    }

    @FXML public void onDairyFilterClick() {

    }

    @FXML public void onProteinFilterClick() {

    }

    @FXML public void onFruitFilterClick() {

    }

    @FXML public void onGrainsFilterClick() {

    }

    @FXML public void onMiscFilterClick() {

    }
}
