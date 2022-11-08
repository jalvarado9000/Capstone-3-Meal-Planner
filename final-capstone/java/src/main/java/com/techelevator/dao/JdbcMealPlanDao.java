package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.MealPlanDay;
import com.techelevator.model.MealPlanDayRecipe;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.MealPlan;

@Service
public class JdbcMealPlanDao implements MealPlanDao
{
    
    private JdbcTemplate jdbcTemplate;
    
    public JdbcMealPlanDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    @Override
    public List<MealPlan> findAllMealPlan(int ownerId)
    {
        String sql = "SELECT * FROM meal_plan WHERE owner_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, ownerId);
        
        List<MealPlan> mealPlanList = new ArrayList<>();
        try
        {
            while (rowSet.next())
            {
                mealPlanList.add(mapRowToMealPlan(rowSet));
            }
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return mealPlanList;
    }
    
    @Override
    public int getPlanIdByOwnerId(Long ownerId)
    {
        
        
        return jdbcTemplate.queryForObject(
                "SELECT * FROM meal_plan WHERE meal_plan.owner_id = ?", int.class, ownerId);
        //need to get a array of all plans with the owner
    }
    
    @Override
    public int getOwnerId(Long ownerId)
    {
        return jdbcTemplate.queryForObject("SELECT owner_id FROM meal_plan WHERE owner_id = ?", int.class, ownerId);
    }
    
    @Override
    public MealPlanDayRecipe[] findAllRecipesInMealPlanDay(Long dayId) throws Exception
    
    {
        List<MealPlanDayRecipe> mealPlanDayRecipeList = new ArrayList<>();
        
        String sql = "SELECT * FROM day_recipe WHERE meal_plan_day_day_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, dayId);
        try
        {
            if (rowSet.next())
            {
                mealPlanDayRecipeList.add(mapRowToMealPlanDayRecipe(rowSet));
            }
        } catch (Exception e)
        {
            throw new Exception("Error querying for meal plan recipes by day");
        }
        MealPlanDayRecipe[] mealPlanDayRecipeArr = mealPlanDayRecipeList.toArray(new MealPlanDayRecipe[mealPlanDayRecipeList.size()]);
        return mealPlanDayRecipeArr;
    }
    
    @Override
    public MealPlanDay[] findAllDayInMealPlan(long planId) throws Exception
    {
        List<MealPlanDay> mealPlanDayList = new ArrayList<>();
        
        String sql = "SELECT * FROM meal_plan_day WHERE day_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, planId);
        try
        {
            if (rowSet.next())
            {
                mealPlanDayList.add(mapRowToMealPlanDay(rowSet));
            }
        } catch (Exception e)
        {
            throw new Exception("Trouble querying meal plan days");
        }
        MealPlanDay[] mealPlanDayArr = new MealPlanDay[mealPlanDayList.size()];
        return mealPlanDayList.toArray(mealPlanDayArr);
    }
    
    @Override
    public MealPlan mapRowToMealPlan(SqlRowSet rs)
    {
        MealPlan mealPlan = new MealPlan();
        try
        {
            mealPlan.setPlanId(rs.getLong("plan_id"));
            mealPlan.setOwnerId(rs.getLong("owner_id"));
            mealPlan.setTitle(rs.getString("title"));
            MealPlanDay[] mealPlanDays = findAllDayInMealPlan(mealPlan.getPlanId());
            mealPlan.setMealPlanDays(mealPlanDays);
            return mealPlan;
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return mealPlan;
    }
    
    MealPlanDay mapRowToMealPlanDay(SqlRowSet rs) throws Exception
    {
        MealPlanDay mealPlanDay = new MealPlanDay();
        
        try
        {
            mealPlanDay.setDayId(rs.getLong("day_id"));
            mealPlanDay.setPlanId(rs.getLong("plan_id"));
            mealPlanDay.setDayInSequence(rs.getDate("day_in_sequence"));
            MealPlanDayRecipe[] mealPlanDayRecipe = findAllRecipesInMealPlanDay(mealPlanDay.getDayId());
            mealPlanDay.setMealPlanDayRecipes(mealPlanDayRecipe);
            return mealPlanDay;
        } catch (Exception e)
        {
            throw new Exception("Error mapping meal plan days");
        }
    }
    
    //TODO: mealPlanDayRecipe needs to hold the recipe it represents, finish recipe query on RecipeDAO to finish building full mealplan
    
    MealPlanDayRecipe mapRowToMealPlanDayRecipe(SqlRowSet rs) throws Exception
    {
        MealPlanDayRecipe mealPlanDayRecipe = new MealPlanDayRecipe();
        
        try
        {
            mealPlanDayRecipe.setMealPlanDayId(rs.getLong("meal_plan_day_day_id"));
            mealPlanDayRecipe.setRecipeId(rs.getLong("recipe_recipe_id"));
            mealPlanDayRecipe.setHeader(rs.getString("header"));
            
            return mealPlanDayRecipe;
        } catch (Exception e)
        {
            throw new Exception("Error mapping meal plan day recipes");
        }
    }
    
    @Override
    public MealPlan findByTitle(String title)
    {
        return null;
    }
    
    @Override
    public int findPlanIdByTitle(String title)
    {
        return 0;
    }
    
    
    @Override
    public boolean createPlan(MealPlan mealPlan)
    {
        String insertMealPlan = "INSERT INTO meal_plan (owner_id, title) VALUES(?, ?) RETURNING  plan_id;";
        try
        {
            mealPlan.setPlanId(jdbcTemplate.queryForObject(insertMealPlan, Long.class, mealPlan.getOwnerId(), mealPlan.getTitle()));
        } catch (DataAccessException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        for (MealPlanDay mealPlanDay : mealPlan.getMealPlanDays())
        {
            mealPlanDay.setPlanId(mealPlan.getPlanId());
            createPlanDay(mealPlanDay);
        }
        return true;
    }
    
    @Override
    public boolean createPlanDay(MealPlanDay mealPlanDay)
    {
        String sql = "INSERT INTO meal_plan_day (plan_id, day_in_sequence) VALUES(?, ?) RETURNING day_id;";
        try
        {
            mealPlanDay.setDayId(jdbcTemplate.queryForObject(sql, Long.class, mealPlanDay.getPlanId(), mealPlanDay.getDayInSequence()));
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        for (MealPlanDayRecipe mealPlanDayRecipe : mealPlanDay.getMealPlanDayRecipes())
        {
            mealPlanDayRecipe.setMealPlanDayId(mealPlanDay.getDayId());
            createMealPlanDayRecipe(mealPlanDayRecipe);
        }
        return true;
    }
    
    @Override
    public boolean createMealPlanDayRecipe(MealPlanDayRecipe mealPlanDayRecipe)
    {
        String sql = "INSERT INTO day_recipe (meal_plan_day_day_id, recipe_recipe_id, header) VALUES(?, ?, ?);";
        try
        {
            jdbcTemplate.update(sql, mealPlanDayRecipe.getMealPlanDayId(), mealPlanDayRecipe.getRecipeId(), mealPlanDayRecipe.getHeader());
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    @Override
    //The following has not been tested.
    public boolean updatePlan(Long planIdToBeChanged, Long ownerId, String newTitle)
    {
        
        String updateMealPlan = "UPDATE meal_plan SET title = ? WHERE plan_id = ? AND owner_id = ?";
        try
        {
            jdbcTemplate.update(updateMealPlan, newTitle, planIdToBeChanged, ownerId);
        } catch (DataAccessException e)
        {
            return false;
        }
        return true;
        
    }
    
    @Override
    public boolean deletePlan(Long planId, int ownerId)
    {
        List<Long> dayIds = new ArrayList<>();
        String findMealPlanDays = "SELECT day_id FROM meal_plan_day WHERE plan_id = ?;";
        String deleteMealPlan = "DELETE FROM meal_plan WHERE plan_id = ? AND owner_id = ?;";
        String deleteMealPlanDays = "DELETE FROM meal_plan_day WHERE plan_id = ?;";
        String deleteMealPlanDayRecipe = "DELETE FROM day_recipe WHERE meal_plan_day_day_id = ?;";
        String checkOwnership = "SELECT * FROM meal_plan WHERE plan_id = ? AND owner_id = ?;";
        try
        {
            SqlRowSet ownershipCheck = jdbcTemplate.queryForRowSet(checkOwnership, planId, ownerId);
            if (ownershipCheck.next())
            {
                SqlRowSet findDayRow = jdbcTemplate.queryForRowSet(findMealPlanDays, planId);
                while (findDayRow.next())
                {
                    dayIds.add(findDayRow.getLong("day_id"));
                }
                for (Long dayId : dayIds)
                {
                    jdbcTemplate.update(deleteMealPlanDayRecipe, dayId);
                }
                jdbcTemplate.update(deleteMealPlanDays, planId);
                jdbcTemplate.update(deleteMealPlan, planId, ownerId);
            }
            else return false;
        } catch (DataAccessException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
        
    }
    
    
}





