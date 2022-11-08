package com.techelevator.controller;


import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.MealPlanDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@Component

public class MealPlanController {


    RecipeDao recipeDao;
    MealPlanDao mealPlanDao;
    UserDao userDao;
    IngredientDao ingredientDao;

    public MealPlanController(UserDao userDao, MealPlanDao mealPlanDao, RecipeDao recipeDao, IngredientDao ingredientDao) {
        this.userDao = userDao;
        this.mealPlanDao = mealPlanDao;
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
    }

    @RequestMapping(value = "/meal", method = RequestMethod.GET)
    Long printMeal(Principal principal) {
        String username = principal.getName();
        User real_username = userDao.findByUsername(username);
        return real_username.getId();


    }

    @RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET)
    List<Recipe> searchForRecipes(@PathVariable String searchString)
    {
        return recipeDao.getAllRecipeByName(searchString);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void userRecipes(@RequestBody Recipe recipe, int value) {
        try {

            switch (value){

                case 1: {
                    //userChooseToDisplayRecipe(recipe.getRecipeId());

                }
                case 2:{
                    //userChooseToUpdateRecipe(recipe);
                }
                case 3:{
                    //userChooseToDeleteRecipe(recipe.getTitle(), recipe.getCreatorId());
                }
                default:{
                    System.out.println("Recipe not found");

                }
            }

        } catch (Exception e) {throw new RuntimeException(e);}

    }


    //Get method the Recipe the user has on the DB.
    @RequestMapping(value = "/UserRecipes", method = RequestMethod.GET)
    public List<Recipe> getListOfRecipes(Principal principal) throws Exception{
        int userId = userDao.findIdByUsername(principal.getName());
        return recipeDao.getRecipeListFromUser(userId);
    }
    
    
    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public List<Recipe> displayAllRecipes() throws Exception {
         return recipeDao.getAllRecipeList();

    }
    
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
    public Recipe displayRecipeByID(@PathVariable Long id) throws Exception {
        
        Recipe recipe = recipeDao.getRecipeById(id);
        recipe.setIngredients(ingredientDao.findIngredientsByRecipeId(recipe.getRecipeId()));
        return recipe;
    }

    //Creates recipe when the values are passed
    //TODO: get principle working to populate recipe creator id
    //TODO: Cleanup commented out code and test stuff
    @RequestMapping(value = "/FormCreate", method = RequestMethod.POST)
    public boolean userSubmitRecipe(@RequestBody Recipe recipe, Principal principal){
        int creatorId = userDao.findIdByUsername(principal.getName());
        recipe.setCreatorId(creatorId);
        boolean worked = true;
        recipe = recipeDao.createRecipe(recipe);
        worked = ingredientDao.postAllIngredientsForRecipe(recipe)? worked : false;
        return worked;

        
    }
    
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT)
    public boolean userUpdateRecipe(@PathVariable Long id, @RequestBody Recipe recipe, Principal principal)
    {
        int creatorId = userDao.findIdByUsername(principal.getName());
        Boolean worked = false;
        if(recipeDao.checkRecipeOwnership(id, creatorId))
        {
            worked = ingredientDao.deleteAllIngredientsFromRecipe(id) ? true : false;
            worked = ingredientDao.postAllIngredientsForRecipe(recipe) ? worked : false;
            worked = recipeDao.updateRecipe(recipe) ? worked : false;
        }
        return worked;
    }

    @RequestMapping(value = "/MealPlans", method = RequestMethod.GET)
    public List<MealPlan> displayAllUserMealPlans(Principal principal)
    {
        int userId = userDao.findIdByUsername(principal.getName());
        List<MealPlan> mealPlanList = mealPlanDao.findAllMealPlan(userId);
        for (MealPlan mealPlan : mealPlanList)
        {
            for (MealPlanDay mealPlanDay : mealPlan.getMealPlanDays())
            {
                for (MealPlanDayRecipe mealPlanDayRecipe : mealPlanDay.getMealPlanDayRecipes())
                {
                    mealPlanDayRecipe.setRecipe(recipeDao.getRecipeById(mealPlanDayRecipe.getRecipeId()));
                }
            }
        }
        return mealPlanList;
    }

    @RequestMapping(value = "/MealPlans/{id}", method = RequestMethod.DELETE)
    public Boolean deleteMealPlan (@RequestBody Long id, Principal principal)
    {
        int userId = userDao.findIdByUsername(principal.getName());
        return mealPlanDao.deletePlan(id, userId);
    }
    
    @RequestMapping(value = "/recipe/{id}", method = RequestMethod.DELETE)
    public Boolean deleteRecipeById (@PathVariable Long id, Principal principal)
    {
        int userId = userDao.findIdByUsername(principal.getName());
        boolean success = false;
        if (recipeDao.checkRecipeOwnership(id, userId))
        {
            success = ingredientDao.deleteAllIngredientsFromRecipe(id)?true : false;
            success = recipeDao.deleteRecipe(id, userId)?success : false;
        }
        return success;
    }












}











