package edu.utsa.cs3443.replate.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class PantryManager {
    public ArrayList<Food> foods = new ArrayList<Food>();
    private final String foodDataFileName = "data/foods.csv";

    public void loadPantry() throws IOException {

        File file = new File(foodDataFileName);

        if (!file.exists()) {
            throw new IOException(
                    "Food data file was not found.");
        }

        ArrayList<Food> loadedFood = new ArrayList<>();

        try (Scanner scnr = new Scanner(file)) {

            // Skip the CSV header.
            if (scnr.hasNextLine()) {
                scnr.nextLine();
            }

            while (scnr.hasNextLine()) {

                String line = scnr.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    Food food =
                            convertLineToFood(line, ",");

                    if (food != null) {
                        loadedFood.add(food);
                    }

                } catch (IllegalArgumentException e) {
                    System.err.println(
                            "Skipping invalid food record: "
                                    + e.getMessage());
                }
            }
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

    public boolean isFoodExists(int id) {

        for (Food food : foods) {
            if (food.getId() == id) {
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

            if (originalFood.getId() == food.getId()) {

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

            if (currentFood.getId() == food.getId()) {

                foods.remove(i);

                if (saveDataToFile()) {
                    System.out.println(
                            foods.size() + " ships saved to file");
                    return true;
                }

                // Restore the ship if saving failed.
                foods.add(i, currentFood);
                return false;
            }
        }

        return false;
    }

    public Food findFood(int id) {
        for (Food food : foods) {
            if (food.getId() == id) {
                return food;
            }
        }
        return null;
    }

    public ArrayList<Food> getFoodPantryList() {
        return foods;
    }

    private String convertFoodToLine(Food food, String delimiter) {

        return food.getName() + delimiter + food.getQuantity() + delimiter +
                food.getUnit() + delimiter + food.getAddedDate() + delimiter +
                food.getExpireDate() + delimiter + food.getNotes() + delimiter +
                food.getCategory() + delimiter + food.getColor();
    }

    private Food convertLineToFood(String line, String delimiter) {
        String[] attributes = line.split(delimiter);
        if (attributes.length != 9) {
            return null;
        }

        return new Food(Integer.parseInt(attributes[0]), attributes[1], Double.parseDouble(attributes[2]), attributes[3],
                LocalDate.parse(attributes[4]), LocalDate.parse(attributes[5]), attributes[6], attributes[7], attributes[8]);

    }

    private boolean saveDataToFile() {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(foodDataFileName))) {

            bw.write("Name,Id Number,Quantity,Unit,Added Date,Expiration Date,Notes,Category,Color");
            bw.newLine();

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
