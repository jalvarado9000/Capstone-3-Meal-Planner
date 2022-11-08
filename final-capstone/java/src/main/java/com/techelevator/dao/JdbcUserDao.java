package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }



	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role) {
        boolean userCreated = false;

        // create user
        String insertUser = "insert into users (username,password_hash,role) values(?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);
    
    
        createDummyData(newUserId);
        return userCreated;
    }
    
    private void createDummyData(int newUserId)
    {
        //create meal_plan when the user creates a account
        String insertMealPlan = "INSERT INTO meal_plan (owner_id, title) VALUES(?,?)";
        try{
            jdbcTemplate.update(insertMealPlan, newUserId, "Insert new meal plan");


        }catch(Exception e){
            e.getCause();
        }
        
        
        //Fills grocery_list with default values.
        String insertGroceryList = "INSERT INTO grocery_list (owner_id) VALUES(?)";
        try{
            jdbcTemplate.update(insertGroceryList, newUserId);
        }catch(Exception e){
            e.getCause();
        }
        
        //Fills recipe with default values.
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        String insertRecipe = "INSERT INTO recipe (creator_id, title, date_added, cooking_time, prep_time, instructions, private, reference_link, video_link) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(insertRecipe, newUserId, "add new title",date,1,1,"Insert new instructions", true, "Insert reference link", "Insert new video link");
        }catch(Exception e){
            e.getCause();
        }
        
        
        //we would also need reviews table
        
        //user_favorites
        String insertUserFavorites = "INSERT INTO user_favorites (users_user_id,recipe_recipe_id) VALUES(?)";
        try{
            jdbcTemplate.update(insertUserFavorites, newUserId,1 );
        }catch(Exception e){
            e.getCause();
        }
/*
        //Not working
         String insertUserPantry = "INSERT INTO user_pantry (users_user_id) VALUES(?)";
        try{
            jdbcTemplate.update(insertUserPantry,newUserId);
        }catch(Exception e){
            e.getCause();
        }

*/
    }
    
    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }
}
