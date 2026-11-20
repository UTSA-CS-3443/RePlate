package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class AddFoodController extends Controller {

    private PantryManager pantryManager;

//    private SceneManager sceneManager;
    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private ComboBox<String> unitBox;
    @FXML private ComboBox<String> categoryBox;
    @FXML private DatePicker expireDatePicker;
    @FXML private TextField notesField;

    @FXML
    public void initialize() {
        pantryManager = PantryManager.getInstance();

        categoryBox.getItems().addAll(
                "Vegetable", "Fruit", "Dairy", "Meat", "Grain", "Bakery", "Pantry");
        unitBox.getItems().addAll(
                "each", "unit", "cup", "bunch", "bag", "package", "bottle", "g", "oz", "lb");
        System.out.println("AddFoodController initialized!");
    }

    public void setPantryManager(PantryManager pantryManager) {
        this.pantryManager = pantryManager;
    }

    @FXML
    public void onAddFoodClick() {
        if (saveFood()) {
            clearFields();
            openDashboardScene();
        }
    }

    @FXML
    public void onSaveAndAddAnotherClick() {
        if (saveFood()) {
            clearFields();
            nameField.requestFocus();
        }
    }

    @FXML
    public void onCancelClick() {
        clearFields();
        openDashboardScene();
    }

    private boolean saveFood() {
        String name = nameField.getText().trim();
        String category = categoryBox.getValue();
        String unit = unitBox.getValue();
        LocalDate expireDate = expireDatePicker.getValue();

        if (name.isEmpty()) {
            showError("Please enter a name.");
            return false;
        }
        if (category == null) {
            showError("Please pick a category.");
            return false;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityField.getText().trim());
        } catch (NumberFormatException e) {
            showError("Quantity must be a number, like 2 or 1.5.");
            return false;
        }

        if (unit == null || unit.isBlank()) {
            showError("Please pick a unit.");
            return false;
        }
        if (expireDate == null) {
            showError("Please pick an expiration date.");
            return false;
        }

        Food food = new Food(
                pantryManager.generateId(category),
                name,
                quantity,
                unit,
                LocalDate.now(),
                expireDate,
                notesField.getText().trim(),
                category,
                "");

        pantryManager.addFood(food);
        return true;
    }

    @FXML
    public void clearFields() {
        nameField.clear();
        quantityField.clear();
        notesField.clear();
        categoryBox.setValue(null);
        unitBox.setValue(null);
        expireDatePicker.setValue(null);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}