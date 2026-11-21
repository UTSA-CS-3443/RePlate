package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ExpiredFoodController extends Controller{

    private PantryManager pm;
    private FilteredList<Food> expiredFoods;

    @FXML
    private ListView<Food> expiredFoodList;

    @FXML
    private Text label_metric_value_1;

    @FXML
    public void initialize() {
        pm = PantryManager.getInstance();

        expiredFoods = new FilteredList<>(pm.getFoodPantryList(),
                food -> food.isExpired());

        label_metric_value_1.setText(expiredFoods.size() + " items");

        expiredFoods.addListener((javafx.collections.ListChangeListener<Food>)
                                          change -> {
            label_metric_value_1.setText(expiredFoods.size() + " items");
        });

        expiredFoodList.setCellFactory(listView -> new ListCell<Food>() {

            private HBox row;
            private ExpiredFoodCellController cellController;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("expired-food-cell.fxml")
                    );

                    row = loader.load();
                    cellController = loader.getController();

                } catch (IOException e) {
                    throw new RuntimeException(
                            "Unable to load expired-food-cell.fxml", e
                    );
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

        expiredFoodList.setItems(expiredFoods);

        System.out.println("ExpireFood Controller");
    }

    public void refreshExpiredFoods() {

        System.out.println("Refreshing expired foods...");

        expiredFoods.setPredicate(food -> food.isExpired());

        expiredFoodList.refresh();
    }

}
