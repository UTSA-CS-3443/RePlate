package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AddFoodController implements SceneAware {

    private PantryManager pantryManager;

    private SceneManager sceneManager;
    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private TextField unitField;
    @FXML private TextField categoryField;
    @FXML private DatePicker expireDatePicker;

    public void setPantryManager(PantryManager pantryManager) {
        this.pantryManager = pantryManager;
    }

    @FXML
    public void addFood() {
        int id = pantryManager.getFoodPantryList().size() + 1;

        Food food = new Food(id,
                nameField.getText(),
                Double.parseDouble(quantityField.getText()),
                unitField.getText(),
                LocalDate.now(),
                expireDatePicker.getValue(),
                categoryField.getText(),
                "");

        pantryManager.addFood(food);
        clearFields();
    }

    @FXML
    public void clearFields() {
        nameField.clear();
        quantityField.clear();
        unitField.clear();
        categoryField.clear();
        expireDatePicker.setValue(null);
    }

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    
    public void returnToDashboard() {
        sceneManager.switchScene("dashboard");
    }
}