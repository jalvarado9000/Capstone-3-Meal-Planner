package com.techelevator.model;

public class UserPantry {
    long userUserId;
    long ingredientIngredientId;
    String note;

    public long getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(long userUserId) {
        this.userUserId = userUserId;
    }

    public long getIngredientIngredientId() {
        return ingredientIngredientId;
    }

    public void setIngredientIngredientId(long ingredientIngredientId) {
        this.ingredientIngredientId = ingredientIngredientId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
