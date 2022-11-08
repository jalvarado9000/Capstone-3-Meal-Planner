package com.techelevator.model;

public class RecipeIngredient {

    long recipeRecipeId;
    long ingredientIngredientId;
    int amount;
    String text;
    //MeasurementTypes measurementTypes;

    public enum MeasurementTypes{
        types;
    }

    public long getRecipeRecipeId() {
        return recipeRecipeId;
    }

    public void setRecipeRecipeId(long recipeRecipeId) {
        this.recipeRecipeId = recipeRecipeId;
    }

    public long getIngredientIngredientId() {
        return ingredientIngredientId;
    }

    public void setIngredientIngredientId(long ingredientIngredientId) {
        this.ingredientIngredientId = ingredientIngredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
