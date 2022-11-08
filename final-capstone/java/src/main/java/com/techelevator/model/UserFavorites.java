package com.techelevator.model;

public class UserFavorites {
    long usersUserId;
    long recipeRecipeId;

    public long getUsersUserId() {
        return usersUserId;
    }

    public void setUsersUserId(long usersUserId) {
        this.usersUserId = usersUserId;
    }

    public long getRecipeRecipeId() {
        return recipeRecipeId;
    }

    public void setRecipeRecipeId(long recipeRecipeId) {
        this.recipeRecipeId = recipeRecipeId;
    }
}
