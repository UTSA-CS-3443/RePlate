package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Optional;
import java.util.function.Consumer;

public class FoodCellController {

    @FXML private ImageView categoryIcon;
    @FXML private Label nameLabel;
    @FXML private Label quantityLabel;
    @FXML private Label expireLabel;

    @FXML private Button editButton;
    @FXML private Button deleteButton;

    private Food food;
    private Consumer<Food> editAction;

    public void setFood(Food food) {
        this.food = food;

        nameLabel.setText(food.getName());

        quantityLabel.setText(
                food.getQuantity() + " " + food.getUnit()
        );

        expireLabel.setText(
                food.getExpireDate().toString()
        );

        categoryIcon.setImage(
                getCategoryIcon(food.getCategory())
        );

        setButtonIcon(editButton, "/images/pencil_icon.png");
        setButtonIcon(deleteButton, "/images/trash_icon.png");
    }

    private void setButtonIcon(Button button, String path) {
        ImageView icon = new ImageView(
                new Image(getClass().getResource(path).toExternalForm())
        );

        icon.setFitWidth(20);
        icon.setFitHeight(20);
        icon.setPreserveRatio(true);

        button.setGraphic(icon);
    }

    private Image getCategoryIcon(String category) {
        String path;

        switch (category.toLowerCase()) {
            case "vegetable":
                path = "/images/vegetable_icon.png";
                break;
            case "fruit":
                path = "/images/fruit_icon.png";
                break;
            case "dairy":
                path = "/images/dairy_icon.png";
                break;
            case "meat":
            case "protein":
                path = "/images/protein_icon.png";
                break;
            case "grain":
            case "bakery":
                path = "/images/grain_icon.png";
                break;
            default:
                path = "/images/misc_icon.png";
                break;
        }

        return new Image(
                getClass().getResource(path).toExternalForm()
        );
    }

    public void setEditAction(Consumer<Food> editAction) {
        this.editAction = editAction;
    }

    @FXML
    private void onEditClick() {

        if (food != null && editAction != null) {
            editAction.accept(food);
        }
    }

    @FXML
    private void onDeleteClick() {

        if (food == null) {
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

        confirmation.setTitle("Delete Food");
        confirmation.setHeaderText("Delete " + food.getName() + "?");
        confirmation.setContentText("Are you sure you want to remove this food from your pantry?");

        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            PantryManager pm = PantryManager.getInstance();
            pm.deleteFood(food);

            System.out.println("Delete: " + food.getName());
        }

    }
}