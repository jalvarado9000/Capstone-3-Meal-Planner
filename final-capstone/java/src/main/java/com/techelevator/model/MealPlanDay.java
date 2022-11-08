package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

public class MealPlanDay
{
    private long dayId;
    private long planId;
    @NotEmpty (message="please enter which day in the mealplan this day is supposed to represent")
    private Date dayInSequence;
    private MealPlanDayRecipe[] mealPlanDayRecipes;
    
    public MealPlanDay()
    {
    }
    
    public MealPlanDay(Date dayInSequence, MealPlanDayRecipe[] mealPlanDayRecipes)
    {
        this.dayInSequence = dayInSequence;
        this.mealPlanDayRecipes = mealPlanDayRecipes;
    }
    
    public long getDayId()
    {
        return dayId;
    }
    
    public void setDayId(long dayId)
    {
        this.dayId = dayId;
    }
    
    public long getPlanId()
    {
        return planId;
    }
    
    public void setPlanId(long planId)
    {
        this.planId = planId;
    }
    
    public Date getDayInSequence()
    {
        return dayInSequence;
    }
    
    public void setDayInSequence(Date dayInSequence)
    {
        this.dayInSequence = dayInSequence;
    }
    
    public MealPlanDayRecipe[] getMealPlanDayRecipes()
    {
        return mealPlanDayRecipes;
    }
    
    public void setMealPlanDayRecipes(MealPlanDayRecipe[] mealPlanDayRecipes)
    {
        this.mealPlanDayRecipes = mealPlanDayRecipes;
    }
}
