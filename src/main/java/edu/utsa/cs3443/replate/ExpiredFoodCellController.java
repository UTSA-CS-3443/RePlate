
package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;

public class ExpiredFoodCellController {

    @FXML private CheckBox selectCheckBox;
    @FXML private ImageView categoryIcon;
    @FXML private Label foodNameLabel;
    @FXML private Label quantityLabel;
    @FXML private Label expirationLabel;

    private Food food;
    private BiConsumer<Food, Boolean> selectionAction;

    public void setSelectionAction(BiConsumer<Food, Boolean> selectionAction) {
        this.selectionAction = selectionAction;
    }

    @FXML
    private void initialize() {
        selectCheckBox.setOnAction(event -> {
            selectionAction.accept(food, selectCheckBox.isSelected());
        });
    }

    public void setSelected(boolean selected) {
        selectCheckBox.setSelected(selected);
    }

    public void setFood(Food food) {
        this.food = food;

        foodNameLabel.setText(food.getName());

        categoryIcon.setImage(
                getCategoryIcon(food.getCategory())
        );

        quantityLabel.setText(
                food.getQuantity() + " " + food.getUnit()
        );

        if (food.getExpireDate() != null) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MMM dd");

            expirationLabel.setText(
                    food.getExpireDate().format(formatter)
            );
        } else {
            expirationLabel.setText("");
        }

        System.out.println("Displaying expired food: " + food.getName());
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

        java.net.URL imageUrl = getClass().getResource(path);

        if (imageUrl == null) {
            System.err.println("Category image not found: " + path);
            return null;
        }

        return new Image(imageUrl.toExternalForm());
        /*
        return new Image(
                getClass().getResource(path).toExternalForm()
        );

         */
    }


}
