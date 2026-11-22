package edu.utsa.cs3443.replate;

import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import edu.utsa.cs3443.replate.model.Food;
import edu.utsa.cs3443.replate.model.PantryManager;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.time.LocalDate;

public class DashboardController extends Controller {
//    private Stage stage;

    @FXML
    public void initialize() {

        System.out.println("DashboardController initialized!");
        PantryManager pm = PantryManager.getInstance();
        LocalDate today = LocalDate.now();
        LocalDate weekFromNow = today.plusDays(7);

        FilteredList<Food> expiring = new FilteredList<>(pm.getFoodPantryList(),
                food -> food.getExpireDate() != null
                    && !food.getExpireDate().isBefore(today)
                    && !food.getExpireDate().isAfter(weekFromNow));

        expiringList.setItems(new SortedList<>(expiring,
                Comparator.comparing(Food::getExpireDate)));

        expiringList.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Food food, boolean empty) {
                super.updateItem(food, empty);
                if (empty || food == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }

                long days = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpireDate());
                String badgeText = days == 0 ? "Today" : days == 1 ? "Tomorrow" : "In " + days + " days";

                double q = food.getQuantity();
                String qty = (q == Math.floor(q) ? String.valueOf((long) q) : String.valueOf(q))
                        + " " + food.getUnit();

                Label name = new Label(food.getName());
                Label quantity = new Label(qty);
                quantity.getStyleClass().add("page-description");

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label badge = new Label(badgeText);
                badge.getStyleClass().add("status-badge");

                HBox row = new HBox(8, name, quantity, spacer, badge);
                row.setAlignment(Pos.CENTER_LEFT);

                setText(null);
                setGraphic(row);
            }
        });
    }

    @FXML
    private Label label_header_date;

    @FXML
    private Label label_header_sub;

    @FXML
    private Button button_add;

    @FXML
    private Label dashboard_detail_header;

    @FXML
    private VBox dashboard_detail;

    @FXML
    private ListView<Food> expiringList;

//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }

}
