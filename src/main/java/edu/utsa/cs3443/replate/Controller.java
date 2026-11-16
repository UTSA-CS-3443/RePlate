package edu.utsa.cs3443.replate;

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
    }
}
