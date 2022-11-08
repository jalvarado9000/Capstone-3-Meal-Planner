package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Array;
import java.util.List;


public interface RecipeDao
{
    //TODO: connect interface to class



        public List<Recipe> getRecipeListFromUser(int creatorId);
        public List<Recipe> getAllRecipeByName(String namesOfRecipe);
        public List<Recipe> getAllRecipeList();
        public Recipe getRecipeById(Long recipeId);

        //public Recipe getRecipeByName(String namesOfRecipe) throws Exception;

        //public boolean createRecipe(Recipe recipe);
        //public boolean updateRecipe(Recipe recipe);


        //public boolean createRecipe(Long creatorId, String title, Long cookingTime, Long prepTime, Ingredient ingredient[], String instructions,
                                   // boolean isPrivate, String[] pictureLinks, String referenceLink, String subHeader);


        public boolean updateRecipe(Recipe recipe);
        
        boolean checkRecipeOwnership(Long recipeId, int creatorId);
        
        public boolean deleteRecipe(Long recipeId, int creatorId);
    
    Recipe createRecipe(Recipe recipe);
}
