package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Optional;

public class UpdateFoodController extends Controller{

    private PantryManager pantryManager;
    private Food selectedFood;

    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private Label label_header_main;
    @FXML private ComboBox<String> categoryBox;
    @FXML private ComboBox<String> unitBox;
    @FXML private TextField notesField;
    @FXML private DatePicker expireDatePicker;

    @FXML
    public void initialize() {
        pantryManager = PantryManager.getInstance();
        categoryBox.getItems().addAll(
                "Vegetable", "Fruit", "Dairy", "Meat", "Grain", "Bakery", "Pantry");
        unitBox.getItems().addAll(
                "each", "unit", "cup", "bunch", "bag", "package", "bottle", "g", "oz", "lb");
    }

    public void setPantryManager(PantryManager pantryManager) {
        this.pantryManager = pantryManager;
    }

    public void setSelectedFood(Food food) {
        selectedFood = food;
        nameField.setText(food.getName());
        quantityField.setText(String.valueOf(food.getQuantity()));
        unitBox.setValue(food.getUnit());
        categoryBox.setValue(food.getCategory());
        notesField.setText(food.getNotes() == null ? "" : food.getNotes());
        label_header_main.setText(food.getName());
        expireDatePicker.setValue(food.getExpireDate());
    }

    @FXML
    public void updateFood() {
        selectedFood.setName(nameField.getText());
        selectedFood.setQuantity(Double.parseDouble(quantityField.getText()));
        selectedFood.setUnit(unitBox.getValue());
        selectedFood.setCategory(categoryBox.getValue());
        selectedFood.setNotes(notesField.getText().trim());
        selectedFood.setExpireDate(expireDatePicker.getValue());

        pantryManager.updateFood(selectedFood);
        openPantryScene();
    }

    @FXML
    public void onRemoveClick() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText("Delete " + selectedFood.getName() + "?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            pantryManager.deleteFood(selectedFood);
            openPantryScene();
        }
    }

    @FXML
    public void onCancelClick() {
        openPantryScene();
    }
}