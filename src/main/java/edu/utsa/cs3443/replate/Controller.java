package edu.utsa.cs3443.replate;
import edu.utsa.cs3443.replate.model.Food;

public class Controller implements SceneAware {
    private SceneManager sceneManager;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void openDashboardScene() {
        sceneManager.switchScene("dashboard");
    }
    public void openAddScene() {
        sceneManager.switchScene("add-food");
    }
    public void openPantryScene() {
        sceneManager.switchScene("food-pantry");
    }
    public void openExpiredFoodScene() {

        sceneManager.switchScene("expired-food");

        ExpiredFoodController controller = (ExpiredFoodController)
                sceneManager.getController("expired-food");

        if (controller != null) {
            controller.refreshExpiredFoods();
        }

    }

    public void openEditScene(Food food) {
        UpdateFoodController editController =
                (UpdateFoodController) sceneManager.getController("edit-food");
        editController.setSelectedFood(food);
        sceneManager.switchScene("edit-food");
    }
}
