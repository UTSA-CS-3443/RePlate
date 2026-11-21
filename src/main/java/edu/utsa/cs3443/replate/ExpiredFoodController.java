package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashSet;

public class ExpiredFoodController extends Controller{

    private PantryManager pm;
    private FilteredList<Food> expiredFoods;

    @FXML
    private ListView<Food> expiredFoodList;

    @FXML
    private Text label_metric_value_1;

    @FXML private CheckBox selectAllCheckBox;
    @FXML private Button button_add;

    private final ObservableSet<Food> selectedFoods = FXCollections.observableSet(new HashSet<>());

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
                    cellController.setSelectionAction((food, selected) -> {
                        if (selected) {
                            selectedFoods.add(food);
                        }
                        else {
                            selectedFoods.remove(food);
                        }

                        updateSelectAllCheckBox();
                    });

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

                // Update each checkbox based on selectedFoods
                cellController.setSelected(
                        selectedFoods.contains(food)
                );

                setText(null);
                setGraphic(row);
            }
        });

        expiredFoodList.setItems(expiredFoods);

        System.out.println("ExpireFood Controller");
    }

    public void refreshExpiredFoods() {

        expiredFoods.setPredicate(food -> food.isExpired());

        selectedFoods.retainAll(expiredFoods);

        expiredFoodList.refresh();

        label_metric_value_1.setText(
                expiredFoods.size() + " items"
        );

        updateSelectAllCheckBox();
    }

    @FXML
    private void onRemoveExpiredFood() {
        if (selectedFoods.isEmpty()) {
            return;
        }

        Alert confirmation = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmation.setTitle("Remove Expired Food");
        confirmation.setHeaderText("Remove selected expired foods?");
        confirmation.setContentText(
                "This will remove " + selectedFoods.size()
                        + " food item(s) from your pantry."
        );

        if (confirmation.showAndWait().orElse(
                ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        for (Food food : new HashSet<>(selectedFoods)) {
            pm.deleteFood(food);
        }

        selectedFoods.clear();
        refreshExpiredFoods();
        updateSelectAllCheckBox();
    }

    private void updateSelectAllCheckBox() {

        boolean allSelected =
                !expiredFoods.isEmpty()
                        && selectedFoods.containsAll(expiredFoods);

        selectAllCheckBox.setSelected(allSelected);
    }

    @FXML
    private void onSelectAllClick() {

        if (selectAllCheckBox.isSelected()) {
            // Select every expired food
            selectedFoods.addAll(expiredFoods);
        } else {
            // Uncheck every expired food
            selectedFoods.clear();
        }

        // Redraw the ListView to update each checkbox
        expiredFoodList.refresh();
    }
}
