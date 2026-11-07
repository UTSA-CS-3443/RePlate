package edu.utsa.cs3443.replate.model;

import java.time.LocalDate;

public class Food implements Conversion{
    private int id;
    private String name;
    private double quantity;
    private String unit;
    private LocalDate addedDate;
    private LocalDate expireDate;
    private String notes;
    private String category;
    private String color;

    // Initialize food with set data
    public Food(int id, String name, double quantity, String unit, LocalDate addedDate, LocalDate expireDate, String notes, String category, String color) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.addedDate = addedDate;
        this.expireDate = expireDate;
        this.notes = notes;
        this.category = category;
        this.color = color;
    }

    // Initialize food with no notes
    public Food(int id, String name, double quantity, String unit, LocalDate addedDate, LocalDate expireDate, String category, String color) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.addedDate = addedDate;
        this.expireDate = expireDate;
        this.notes = null;
        this.category = category;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isExpired() {
        return false;
    }

    public double convertUnitToGram(double quantity, String unit) {
        if (unit.equalsIgnoreCase("ounce")) {
            return quantity * 28.35;
        }
        else if (unit.equalsIgnoreCase("pounds")) {
            return quantity * 453.59;
        }
        else if (unit.equalsIgnoreCase("grams")){
            return quantity;
        }
        else {
            return 0.0;
        }
    }
}
