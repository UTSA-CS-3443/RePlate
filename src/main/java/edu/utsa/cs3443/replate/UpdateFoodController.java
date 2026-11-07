package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UpdateFoodController {

    private PantryManager pantryManager;
    private Food selectedFood;

    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private TextField unitField;
    @FXML private DatePicker expireDatePicker;

    public void setPantryManager(PantryManager pantryManager) {
        this.pantryManager = pantryManager;
    }

    public void setSelectedFood(Food food) {
        selectedFood = food;
        nameField.setText(food.getName());
        quantityField.setText(String.valueOf(food.getQuantity()));
        unitField.setText(food.getUnit());
        expireDatePicker.setValue(food.getExpireDate());
    }

    @FXML
    public void updateFood() {
        selectedFood.setName(nameField.getText());
        selectedFood.setQuantity(Double.parseDouble(quantityField.getText()));
        selectedFood.setUnit(unitField.getText());
        selectedFood.setExpireDate(expireDatePicker.getValue());

        pantryManager.updateFood(selectedFood);
    }
}