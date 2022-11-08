package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
@CrossOrigin
@Service
//TODO: recipe map -> ingredient query for relational table that holds recipe ingredients to fill array of ingredients for Recipe.ingredients
public class JdbcRecipeDao implements RecipeDao
{
    private JdbcTemplate jdbcTemplate;
    
    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static Connection getConnection(String url,
                                           Properties info)
    {
        return null;
    }
    
    @Override
    public List<Recipe> getRecipeListFromUser(int creatorId){

        List<Recipe> recipes = new ArrayList<>();

        String sql = "SELECT * FROM recipe WHERE creator_id = ?";

        SqlRowSet recipeList = jdbcTemplate.queryForRowSet(sql, creatorId);
        mapRowToRecipe(recipeList);

        return recipes;
    }


    public List<Recipe> getAllRecipeList(){

        List<Recipe> recipeList = new ArrayList<>();

        String sql = "SELECT * FROM recipe";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        try
        {
            while(rs.next())
            {

                recipeList.add(mapRowToRecipe(rs));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return recipeList;

    }


    @Override
    public Recipe getRecipeById(Long recipeId)
    {
        String sql = "SELECT * FROM recipe WHERE recipe_id = ?;";
    
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipeId);
        
        try
        {
            if(rowSet.next())
            {
                return mapRowToRecipe(rowSet);
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Recipe> getAllRecipeByName(String namesOfRecipe) {

        String sql = "SELECT * FROM recipe WHERE title ILIKE ? ";

        List<Recipe> recipesNames = new ArrayList<>();

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, namesOfRecipe);

        try
        {
            while(rowSet.next())
            {
                recipesNames.add(mapRowToRecipe(rowSet));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());;
        }
        return recipesNames;
    }


    

    public Recipe mapRowToRecipe(SqlRowSet rs)
    {
        Recipe recipe = new Recipe();
        try
        {
            recipe.setRecipeId(rs.getLong(1));
            recipe.setCreatorId(rs.getInt(2));
            recipe.setTitle(rs.getString(3));
            recipe.setDateAdded(rs.getDate(4));
            recipe.setCookingTime(rs.getInt(5));
            recipe.setPrepTime(rs.getInt(6));
            recipe.setInstructions(rs.getString(7));
            recipe.setPrivate(rs.getBoolean(8));
            //Array a = rs.getArray("picture_links");
            //recipe.setPictureLinks((String[])a.getArray());
            recipe.setReferenceLink(rs.getString(9));
            recipe.setSubHeader(rs.getString(10));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        return recipe;
    }

    //creates and store data on the recipe table
    //TODO: take ingredient do a recipe join submit
    public Recipe createRecipe(Recipe recipe) {

        //making a query to the db in order to check title
        //String titleIsFound = "SELECT title FROM recipe WHERE title = ? AND creator_id = ? ";
        //String databaseValue = jdbcTemplate.queryForObject(titleIsFound,String.class, recipe.getTitle(), recipe.getRecipeId());

        //checks if title is already in the db
        //if(recipe.getTitle().equals(databaseValue)){
        //  return false;
       //}
        /*else {*/
    

            //gets the current date
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            recipe.setDateAdded(date);
            System.out.println(recipe);
            //query for items to be inserted in the recipe db
            String sql = "INSERT INTO recipe (creator_id, title, date_added, cooking_time, prep_time, instructions, private, reference_link, picture_links, subheader) VALUES(?,?,?,?,?,?,?,?,?,?) RETURNING recipe_id;";
            try {
                //KeyHolder keyHolder = new GeneratedKeyHolder();
                recipe.setRecipeId(jdbcTemplate.queryForObject(sql, Long.class, recipe.getCreatorId(), recipe.getTitle(), date, recipe.getCookingTime(), recipe.getPrepTime(),
                        recipe.getInstructions(), recipe.isPrivate(), recipe.getReferenceLink(), recipe.getPictureLinks(), recipe.getSubHeader()));
                System.out.println("Success");
            } catch (DataAccessException e) {
                System.err.println(e.getMessage());
            }
            
            return recipe;
       /* }*/
    }


/*
    public boolean createRecipe(Recipe recipe) {

        //making a query to the db in order to check title
        String titleIsFound = "SELECT title FROM recipe WHERE title = ? AND creator_id = ? ";
        String databaseValue = jdbcTemplate.queryForObject(titleIsFound,String.class, recipe.getTitle(), recipe.getCreatorId());

        //checks if title is already in the db
        if(recipe.getTitle().equals(databaseValue)){
            return false;
        }
        else {


            //gets the current date
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            //query for items to be inserted in the recipe db
            String insertMealPlan = "INSERT INTO recipe (creator_id, title, date_added, cooking_time, prep_time, instructions, private, picture_links, reference_link, video_link) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try {
                jdbcTemplate.update(insertMealPlan, recipe.getCreatorId(), recipe.getTitle(), date, recipe.getCookingTime(), recipe.getPrepTime(),
                        recipe.getInstructions(), recipe.isPrivate(), recipe.getPictureLinks(), recipe.getReferenceLink(), recipe.getSubHeader());
            } catch (DataAccessException e) {
                return false;
            }
            return true;
        }
    }

*/
    //updates value on recipe table
    public boolean updateRecipe(Recipe recipe){

        //gets current date
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        
        //query to update db values on recipe table
        String updateRecipeSql = "UPDATE recipe SET title = ?, date_added = ?, cooking_time = ?, prep_time = ?, instructions =?, private = ?, " +
                "picture_links = ?, reference_link = ?, subheader = ? WHERE recipe_id = ? AND creator_id = ?";
        try{
            jdbcTemplate.update(updateRecipeSql, recipe.getTitle(), date, recipe.getCookingTime(), recipe.getPrepTime(),
                    recipe.getInstructions(), recipe.isPrivate(), recipe.getPictureLinks(), recipe.getReferenceLink(), recipe.getSubHeader(), recipe.getRecipeId(), recipe.getCreatorId());
        }catch(DataAccessException e){
            return false;
        }
        return true;

    }

/*

    public boolean updateRecipe(Recipe recipe){

        //gets current date
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        //query to update db values on recipe table
        String updateRecipeSql = "UPDATE recipe SET title = ?, date_added = ?, cooking_time = ?, prep_time = ?, instructions =?, private = ?, " +
                "picture_links = ?, reference_link = ?, video_link = ? WHERE recipe_id = ? AND creator_id = ?";
        try{
            jdbcTemplate.update(updateRecipeSql, recipe.getTitle(), date, recipe.getCookingTime(), recipe.getPrepTime(),
                    recipe.getInstructions(), recipe.isPrivate(), recipe.getPictureLinks(), recipe.getReferenceLink(), recipe.getSubHeader(), recipe.getRecipeId(), recipe.getCreatorId());
        }catch(DataAccessException e){
            return false;
        }
        return true;

    }

*/

    @Override
    public boolean checkRecipeOwnership(Long recipeId, int creatorId)
    {
        String sql = "SELECT * FROM recipe WHERE recipe_id = ? AND creator_id = ?;";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipeId, creatorId);
            return rowSet.next();
        }
        catch (DataAccessException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    //deletes values on recipe table
    public boolean deleteRecipe(Long recipeId, int creatorId){

        //query for deleting item
        String deleteRecipeSql = "DELETE FROM recipe WHERE recipe_id = ? AND creator_id = ? ";
        try{
            jdbcTemplate.update(deleteRecipeSql, recipeId, creatorId);
        }catch(DataAccessException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;

    }














}
