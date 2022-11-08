package com.techelevator.model;

public class Reviews {

    long reviewId;
    long recipe_id;
    String reviewText;
    String reviewScore;
    int reviewerId;

    AlterationLevel alterationLevel;

    public enum AlterationLevel{
        alterationLevelType;
    }

    public AlterationLevel getAlterationLevel() {
        return alterationLevel;
    }

    public void setAlterationLevel(AlterationLevel alterationLevel) {
        this.alterationLevel = alterationLevel;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(String reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }
}
