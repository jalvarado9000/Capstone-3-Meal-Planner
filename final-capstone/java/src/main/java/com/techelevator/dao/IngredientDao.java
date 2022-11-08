package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface IngredientDao
{
    boolean deleteAllIngredientsFromRecipe(Long recipeId);
    
    boolean postAllIngredientsForRecipe(Recipe recipe);
    
    boolean postIngredientForRecipe(Ingredient ingredient, Long recipeId);
    
    Ingredient[] findIngredientsByRecipeId(long recipeId) throws Exception;
    
    Ingredient mapRowToIngredient(SqlRowSet rs) throws Exception;
}
