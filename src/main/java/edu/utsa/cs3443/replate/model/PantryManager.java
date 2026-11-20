package edu.utsa.cs3443.replate.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PantryManager {
    public ObservableList<Food> foods = FXCollections.observableArrayList();
    private final String foodDataFileName;

    private static PantryManager instance;

    private PantryManager() {
        foodDataFileName = "data/RePlateFoodPantry.csv";
    }

    public static PantryManager getInstance() {
        if (instance == null) {
            instance = new PantryManager();
            instance.loadPantry();
        }
        return instance;
    }

    public void loadPantry() {

        Scanner scnr = null;
        ArrayList<Food> loadedFood = new ArrayList<>();

        try {
            String line;
            Food food;
            scnr = new Scanner(new File(foodDataFileName));

            // Skip the CSV header.
            if (scnr.hasNextLine()) {
                scnr.nextLine();
            }

            while(scnr.hasNextLine()) {
                line = scnr.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                food = convertLineToFood(line, ",");
                if (food != null) {
                    loadedFood.add(food);
                }
            }
            scnr.close();

        } catch (FileNotFoundException fnfEx) {
            System.out.println("File not found: " + fnfEx.getMessage());

        } catch (IOException e) {
            System.out.println("Error reading food pantry file: " + e.getMessage());
        }

        foods.clear();
        foods.addAll(loadedFood);
    }

    public boolean addFood(Food food) {
        if (food != null) {
            foods.add(food);
            saveDataToFile();
            return true;
        }
        return false;
    }

    public boolean isFoodExists(String id) {

        for (Food food : foods) {
            if (food.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean updateFood(Food food) {

        if (food == null) {
            return false;
        }

        for (int i = 0; i < foods.size(); i++) {

            Food originalFood = foods.get(i);

            if (originalFood.getId().equals(food.getId())) {

                foods.set(i, food);

                if (saveDataToFile()) {
                    return true;
                }

                // Restore the original food if saving failed.
                foods.set(i, originalFood);
                return false;
            }
        }

        return false;
    }

    public boolean deleteFood(Food food) {

        if (food == null) {
            return false;
        }

        for (int i = 0; i < foods.size(); i++) {

            Food currentFood = foods.get(i);

            if (currentFood.getId().equals(food.getId())) {

                foods.remove(i);

                if (saveDataToFile()) {
                    System.out.println(
                            foods.size() + " food items saved to file");
                    return true;
                }

                // Restore the item if saving failed.
                foods.add(i, currentFood);
                return false;
            }
        }

        return false;
    }

    public Food findFood(String id) {
        for (Food food : foods) {
            if (food.getId().equals(id)) {
                return food;
            }
        }
        return null;
    }

    public ObservableList<Food> getFoodPantryList() {
        return foods;
    }

    private String convertFoodToLine(Food food, String delimiter) {
        String notes = food.getNotes() == null ? "" : food.getNotes().replace(",", ";");
        return food.getId() + delimiter + food.getName() + delimiter +
                food.getCategory() + delimiter + food.getQuantity() + delimiter +
                food.getUnit() + delimiter + food.getAddedDate() + delimiter +
                food.getExpireDate() + delimiter + notes;
    }

    private Food convertLineToFood(String line, String delimiter) {
        String[] attributes = line.split(delimiter, -1);
        if (attributes.length != 8) {
            return null;
        }
        return new Food(attributes[0], attributes[1], Double.parseDouble(attributes[3]), attributes[4],
                LocalDate.parse(attributes[5]), LocalDate.parse(attributes[6]), attributes[7], attributes[2], "");
    }

    public String generateId(String category) {
        String prefix = (category == null || category.length() < 3)
                ? "MSC" : category.substring(0, 3).toUpperCase();
        int max = 0;
        for (Food food : foods) {
            String id = food.getId();
            int dash = id.lastIndexOf('-');
            try {
                max = Math.max(max, Integer.parseInt(id.substring(dash + 1)));
            } catch (NumberFormatException ignored) { }
        }
        return String.format("RP-%s-%04d", prefix, max + 1);
    }

    private boolean saveDataToFile() {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(foodDataFileName))) {

            bw.write("item_id,food_name,category,amount,unit,purchased_on,best_before,notes");            bw.newLine();

            for (Food food : foods) {
                bw.write(convertFoodToLine(food, ","));
                bw.newLine();
            }

            return true;

        } catch (IOException e) {
            System.err.println(
                    "Error saving food items data to file: " + e.getMessage());
            return false;
        }
    }
}
