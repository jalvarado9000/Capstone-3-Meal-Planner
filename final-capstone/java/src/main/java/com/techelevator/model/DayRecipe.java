package com.techelevator.model;

public class DayRecipe {
    long mealPlanDayDayId;
    long recipeRecipeId;
    String header;

    public long getMealPlanDayDayId() {
        return mealPlanDayDayId;
    }

    public void setMealPlanDayDayId(long mealPlanDayDayId) {
        this.mealPlanDayDayId = mealPlanDayDayId;
    }

    public long getRecipeRecipeId() {
        return recipeRecipeId;
    }

    public void setRecipeRecipeId(long recipeRecipeId) {
        this.recipeRecipeId = recipeRecipeId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
