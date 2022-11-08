package com.techelevator.dao;

import com.techelevator.model.MealPlan;
import com.techelevator.model.MealPlanDay;
import com.techelevator.model.MealPlanDayRecipe;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface MealPlanDao {
    
    List<MealPlan> findAllMealPlan(int ownerId);
    
    int getPlanIdByOwnerId(Long ownerId);

    int getOwnerId(Long ownerId);
    
    MealPlanDayRecipe[] findAllRecipesInMealPlanDay(Long dayId) throws Exception;
    
    MealPlanDay[] findAllDayInMealPlan(long planId) throws Exception;
    
    MealPlan mapRowToMealPlan(SqlRowSet rs) throws Exception;
    
    MealPlan findByTitle(String title);

    int findPlanIdByTitle(String title);
    
    boolean createPlan(MealPlan mealPlan);
    
    boolean createPlanDay(MealPlanDay mealPlanDay);
    
    boolean createMealPlanDayRecipe(MealPlanDayRecipe mealPlanDayRecipe);
    
    //The following has not been tested.
    boolean updatePlan(Long planIdToBeChanged, Long ownerId, String newTitle);
    
    boolean deletePlan(Long planId, int ownerId);
}
