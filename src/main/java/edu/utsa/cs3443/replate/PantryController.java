package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;

import java.io.IOException;

public class PantryController extends Controller{
    private PantryManager pm;
    private ObservableList<Food> masterPantry;
    private FilteredList<Food> filteredPantry;
    private String selectedCategory = "All";

    @FXML
    private ListView<Food> pantryList;

    @FXML
    private TextField searchBar;



    public PantryController(){
        System.out.println("PantryController Instantiated");
    }

    @FXML
    public void initialize() {
        pm = PantryManager.getInstance();
        masterPantry = pm.getFoodPantryList();

        filteredPantry = new FilteredList<>(
                masterPantry, food -> true);

        pantryList.setItems(filteredPantry);

        pantryList.setCellFactory(listView -> new ListCell<Food>() {

            private HBox row;
            private FoodCellController cellController;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("food-cell.fxml"));

                    row = loader.load();
                    cellController = loader.getController();

                    cellController.setEditAction(food -> openEditFood(food));
                } catch (IOException e) {
                    throw new RuntimeException("Unable to load food-cell.fxml", e);
                }
            }

            @Override
            protected void updateItem(Food food, boolean empty) {


                super.updateItem(food, empty);

                if (empty || food == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }

                cellController.setFood(food);

                setText(null);
                setGraphic(row);
            }
        });
    }

    private void openEditFood(Food food) {
        System.out.println("Editing: " + food.getName());
        openEditScene(food);
    }

    private void applyFilters() {
        String searchText = searchBar.getText().trim().toLowerCase();

        filteredPantry.setPredicate(food -> {

            if (food == null) {
                return false;
            }

            boolean matchesSearch =
                    searchText.isEmpty() ||
                    (food.getName() != null &&
                    food.getName().toLowerCase().contains(searchText));

            boolean matchesCategory = false;

            String category = food.getCategory();

            if (category == null) {
                category = "";
            }

            if (selectedCategory.equals("All")) {
                matchesCategory = true;
            }
            else if (selectedCategory.equals("Vegetable")) {
                matchesCategory = category.equalsIgnoreCase("Vegetable") ||
                category.equalsIgnoreCase("Vegetables");
            }
            else if (selectedCategory.equals("Fruit")) {
                matchesCategory = category.equalsIgnoreCase(("Fruit")) ||
                category.equalsIgnoreCase("Fruits");
            }
            else if (selectedCategory.equals("Protein")) {
                matchesCategory = category.equalsIgnoreCase("Protein") ||
                category.equalsIgnoreCase("Meat");
            }
            else if (selectedCategory.equals("Grains")) {
                matchesCategory = category.equalsIgnoreCase("Grains") ||
                        category.equalsIgnoreCase("Grain") ||
                        category.equalsIgnoreCase("Bakery");
            }
            else if (selectedCategory.equals("Dairy")) {
                matchesCategory = category.equalsIgnoreCase("Dairy");
            }
            else if (selectedCategory.equals("Misc")) {
                matchesCategory = !category.equalsIgnoreCase("Vegetables")
                        && !category.equalsIgnoreCase("Vegetable")
                        && !category.equalsIgnoreCase("Fruit")
                        && !category.equalsIgnoreCase("Fruits")
                        && !category.equalsIgnoreCase("Dairy")
                        && !category.equalsIgnoreCase("Grain")
                        && !category.equalsIgnoreCase("Grains")
                        && !category.equalsIgnoreCase("Bakery")
                        && !category.equalsIgnoreCase("Protein")
                        && !category.equalsIgnoreCase("Meat");
            }

            return matchesSearch && matchesCategory;
        });
    }

    @FXML
    public void onSearchClick() {
        applyFilters();
    }

    @FXML
    public void onAllFilterClick() {
        selectedCategory = "All";
        applyFilters();
    }

    @FXML public void onVeggieFilterClick() {
        selectedCategory = "Vegetable";
        applyFilters();
    }

    @FXML public void onDairyFilterClick() {
        selectedCategory = "Dairy";
        applyFilters();
    }

    @FXML public void onProteinFilterClick() {
        selectedCategory = "Protein";
        applyFilters();
    }

    @FXML public void onFruitFilterClick() {
        selectedCategory = "Fruit";
        applyFilters();
    }

    @FXML public void onGrainsFilterClick() {
        selectedCategory = "Grains";
        applyFilters();
    }

    @FXML public void onMiscFilterClick() {
        selectedCategory = "Misc";
        applyFilters();
    }
}
