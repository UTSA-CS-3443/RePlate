package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.scene.control.*;

public class PantryController extends Controller{
    private PantryManager pm;

    private ObservableList<Food> masterPantry;

    @FXML
    private ListView<Food> pantryList;


    public PantryController(){
        System.out.println("PantryController Instantiated");
    }

    @FXML
    public void initialize() {
        pm = PantryManager.getInstance();
        masterPantry = pm.getFoodPantryList();

        pantryList.setItems(masterPantry);

        pantryList.setCellFactory(listView -> new ListCell<Food>() {

            @Override
            protected void updateItem(Food food, boolean empty) {

                super.updateItem(food, empty);

                if (empty || food == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }

                HBox row = new HBox(15);
                row.setAlignment(Pos.CENTER_LEFT);
                row.getStyleClass().add("food-row");

                // Category icon
                ImageView categoryIcon = new ImageView(
                        getCategoryIcon(food.getCategory())
                );
                categoryIcon.setFitWidth(28);
                categoryIcon.setFitHeight(28);
                categoryIcon.setPreserveRatio(true);

                // Food name
                Label nameLabel = new Label(food.getName());
                nameLabel.getStyleClass().add("food-name");

                // Quantity
                Label quantityLabel = new Label(
                        food.getQuantity() + " " + food.getUnit()
                );

                // Expiration date
                Label expireLabel = new Label(
                        food.getExpireDate().toString()
                );
                expireLabel.getStyleClass().add("expiration-badge");

                // Edit icon
                ImageView editIcon = new ImageView(
                        getClass().getResource(
                                "/images/pencil_icon.png"
                        ).toExternalForm()
                );
                editIcon.setFitWidth(20);
                editIcon.setFitHeight(20);
                editIcon.setPreserveRatio(true);

                Button editButton = new Button();
                editButton.setGraphic(editIcon);
                editButton.getStyleClass().add("more-options");

                // Delete icon
                ImageView deleteIcon = new ImageView(
                        getClass().getResource(
                                "/images/trash_icon.png"
                        ).toExternalForm()
                );
                deleteIcon.setFitWidth(20);
                deleteIcon.setFitHeight(20);
                deleteIcon.setPreserveRatio(true);

                Button deleteButton = new Button();
                deleteButton.setGraphic(deleteIcon);
                deleteButton.getStyleClass().add("more-options");

                // Flexible spacing
                Region spacer1 = new Region();
                Region spacer2 = new Region();
                Region spacer3 = new Region();

                HBox.setHgrow(spacer1, Priority.ALWAYS);
                HBox.setHgrow(spacer2, Priority.ALWAYS);
                HBox.setHgrow(spacer3, Priority.ALWAYS);

                row.getChildren().addAll(
                        categoryIcon,
                        nameLabel,
                        spacer1,
                        quantityLabel,
                        spacer2,
                        expireLabel,
                        spacer3,
                        editButton,
                        deleteButton
                );

                setText(null);
                setGraphic(row);
            }
        });
    }

    private Image getCategoryIcon(String category) {
        String iconPath;

        switch(category.toLowerCase()) {

            case "vegetable":
                iconPath = "/images/vegetable_icon.png";
                break;
            case "fruit":
                iconPath = "/images/fruit_icon.png";
                break;

            case "dairy":
                iconPath = "/images/dairy_icon.png";
                break;

            case "grain":
            case "bakery":
                iconPath = "/images/grain_icon.png";
                break;

            case "meat":
            case "protein":
                iconPath = "/images/protein_icon.png";
                break;

            default:
                iconPath = "/images/misc_icon.png";
                break;

        }

        return new Image(getClass().getResource(iconPath).toExternalForm());
    }

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
