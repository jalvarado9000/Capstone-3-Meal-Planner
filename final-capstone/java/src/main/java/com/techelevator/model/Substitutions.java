package com.techelevator.model;

public class Substitutions {
    long ingredientId;
    long substitutionId;
    int ingredientAmount;
    /*
    //setter and getter have not been implemented.
    MeasurementTypes ingredientMeasurement;
    MeasurementTypes substituteMeasurement;
     */

    int substituteAmount;
    String instructions;

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public long getSubstitutionId() {
        return substitutionId;
    }

    public void setSubstitutionId(long substitutionId) {
        this.substitutionId = substitutionId;
    }

    public int getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(int ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public int getSubstituteAmount() {
        return substituteAmount;
    }

    public void setSubstituteAmount(int substituteAmount) {
        this.substituteAmount = substituteAmount;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
