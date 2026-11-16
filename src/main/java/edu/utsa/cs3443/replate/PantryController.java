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
        pm.loadPantry();
    }

    private ObservableList<Food> masterPantry = FXCollections.observableArrayList(pm.getFoodPantryList());


    @FXML
    public void onFilterClick() {

    }

    @FXML
    public void onAddFoodButtonClick() {

    }

    @FXML
    public void onEditButtonClick() {

    }

    @FXML
    public void onRemoveButtonClick() {

    }

    @FXML
    public void displayPantry() {

    }
}
