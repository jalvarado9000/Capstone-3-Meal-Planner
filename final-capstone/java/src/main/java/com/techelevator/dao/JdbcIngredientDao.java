package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@Service
public class JdbcIngredientDao implements IngredientDao
{
    private JdbcTemplate jdbcTemplate;
    
    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public boolean deleteAllIngredientsFromRecipe(Long recipeId)
    {
        String sql = "DELETE FROM recipe_ingredient WHERE recipe_recipe_id = ?;";
        try
        {
            jdbcTemplate.update(sql, recipeId);
            return true;
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean postAllIngredientsForRecipe(Recipe recipe)
    {
        boolean worked = true;
        for (Ingredient i : recipe.getIngredients())
        {
            try
            {
                if(postIngredientForRecipe(i, recipe.getRecipeId()) == false)
                {
                    worked = false;
                }
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                return false;
            }

        }
        return worked;
    }
    
    @Override
    public boolean postIngredientForRecipe(Ingredient ingredient, Long recipeId)
    {
        String sql = "INSERT INTO recipe_ingredient (recipe_recipe_id, ingredient_ingredient_id, amount, note) VALUES (?,?,?,?);";
        
        try {
            jdbcTemplate.update(sql, recipeId, ingredient.getIngredientId(), ingredient.getAmount()
                    , ingredient.getRecipeNote());
        }
        catch (DataAccessException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    @Override
    public Ingredient[] findIngredientsByRecipeId(long recipeId) throws Exception
    {
        List<Ingredient> ingredientList = new ArrayList<>();
        
        String sql = "SELECT * FROM recipe_ingredient " +
                "LEFT OUTER JOIN ingredient ON recipe_ingredient.ingredient_ingredient_id = ingredient.ingredient_id " +
                "WHERE recipe_ingredient.recipe_recipe_id = ?;";
        
        try
        {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipeId);
            while (rowSet.next())
            {
                ingredientList.add(mapRowToIngredient(rowSet));
            }
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    
        Ingredient[] ingredientArr = new Ingredient[ingredientList.size()];
        return ingredientList.toArray(ingredientArr);
    }
    
    @Override
    public Ingredient mapRowToIngredient(SqlRowSet rs) throws Exception
    {
        Ingredient ingredient = new Ingredient();
        try
        {
            ingredient.setIngredientId(rs.getLong("ingredient_id"));
            ingredient.setIngredientName(rs.getString("ingredient_name"));
            //ingredient.setIngredientTypes(IngredientTypes.valueOf(rs.getString("ingredient_types")));
            //ingredient.setSuperCategory(rs.getLong("super_category"));
            ingredient.setRecipeNote(rs.getString("note"));
            return ingredient;
        }
        catch (Exception e)
        {
            throw new Exception("Error mapping ingredient");
        }
    }
}
