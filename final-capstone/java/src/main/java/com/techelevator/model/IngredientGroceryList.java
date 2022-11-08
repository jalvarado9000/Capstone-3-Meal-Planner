package com.techelevator.model;

public class IngredientGroceryList {
    long ingredientIngredientId;
    long groceryListListId;
    long sublistId;
    int amount;
    String userNote;
    boolean crossedOff;
    boolean favorite;
    MeasurementTypes measurementTypes;
    public enum MeasurementTypes{
        types;
    }

    public MeasurementTypes getMeasurementTypes() {
        return measurementTypes;
    }

    public void setMeasurementTypes(MeasurementTypes measurementTypes) {
        this.measurementTypes = measurementTypes;
    }

    public long getIngredientIngredientId() {
        return ingredientIngredientId;
    }

    public void setIngredientIngredientId(long ingredientIngredientId) {
        this.ingredientIngredientId = ingredientIngredientId;
    }

    public long getGroceryListListId() {
        return groceryListListId;
    }

    public void setGroceryListListId(long groceryListListId) {
        this.groceryListListId = groceryListListId;
    }

    public long getSublistId() {
        return sublistId;
    }

    public void setSublistId(long sublistId) {
        this.sublistId = sublistId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public boolean isCrossedOff() {
        return crossedOff;
    }

    public void setCrossedOff(boolean crossedOff) {
        this.crossedOff = crossedOff;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
