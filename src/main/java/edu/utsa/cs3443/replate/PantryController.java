package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;

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

            private HBox row;
            private FoodCellController cellController;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("food-cell.fxml"));

                    row = loader.load();
                    cellController = loader.getController();
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
