package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MealPlan {

    private Long planId;
    private Long ownerId;
    private String title;
    private MealPlanDay[] mealPlanDays;
    
    public MealPlan()
    {
    
    }
    
    public MealPlan(Long ownerId, String title, MealPlanDay[] mealPlanDays)
    {
        this.ownerId = ownerId;
        this.title = title;
        this.mealPlanDays = mealPlanDays;
    }
    
    public void setPlanId(Long planId) {
        this.planId = planId;
    }
    
    public Long getPlanId() { return planId; }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public MealPlanDay[] getMealPlanDays()
    {
        return mealPlanDays;
    }
    
    public void setMealPlanDays(MealPlanDay[] mealPlanDays)
    {
        this.mealPlanDays = mealPlanDays;
    }
    
    @Override
    public String toString() {
        return "MealPlan{" +
                "planId=" + planId +
                ", ownerId=" + ownerId +
                ", title='" + title + '\'' +
                '}';
    }
}
