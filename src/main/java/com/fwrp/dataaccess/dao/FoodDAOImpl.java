/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.models.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class implements the {@link FoodDAO} interface to provide concrete methods for
 * performing operations on food data in a data source. It provides implementations to
 * add, retrieve, update, and remove food items.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class FoodDAOImpl implements FoodDAO {

    /**
     * Adds a new food item to the data source.
     * 
     * @param food The {@link Food} object to be added.
     * @param conn SQL connection
     * @return boolean Returns true if the food was successfully added, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean addFood(Food food, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO foods (name, expire_days, unitprice, discount)"
                            + "VALUES(?, ?, ?, ?)")) {
            
            pstmt.setString(1, food.getName());
            pstmt.setInt(2, food.getExpireDays());
            pstmt.setDouble(3, food.getUnitPrice());
            pstmt.setDouble(4, food.getDiscount());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }            
        } 
        
        return isSuccess;
    }

    /**
     * Retrieves all food items from the data source.
     * 
     * @param conn SQL connection
     * @return ArrayList&lt;Food&gt; A list of {@link Food} objects.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public ArrayList<Food> getAllFoods(Connection conn) throws SQLException{
        ArrayList<Food> foods = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM foods ORDER BY name ASC")){
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Food food = new Food();
                    food.setId(rs.getInt("id"));
                    food.setName(rs.getString("name"));
                    food.setExpireDays(rs.getInt("expire_days"));
                    food.setUnitPrice(rs.getDouble("unitprice"));
                    food.setDiscount(rs.getDouble("discount"));
                    
                    foods.add(food);
                }
            }
        }
        
        return foods;
    }
    
    /**
     * Retrieves a food item by its ID.
     * 
     * @param foodId The ID of the food to retrieve.
     * @param conn SQL connection
     * @return Food Returns the {@link Food} object if found, null otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Food getFoodById(int foodId, Connection conn)  throws SQLException{       
        Food food = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM foods WHERE id = ?")){            
            pstmt.setInt(1, foodId);
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    food = new Food(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("expire_days"),
                        rs.getDouble("unitprice"),
                        rs.getDouble("discount")
                    );
                }
            }            
        } 
        
        return food;
    }
    
     /**
     * Retrieves a food item by its name.
     * 
     * @param name The name of the food to retrieve.
     * @param conn SQL connection
     * @return Food Returns the {@link Food} object if found, null otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Food getFoodByName(String name, Connection conn)  throws SQLException{    
        Food food = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM foods WHERE name = ?")){           
            pstmt.setString(1, name);
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    food = new Food(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("expire_days"),
                        rs.getDouble("unitprice"),
                        rs.getDouble("discount")
                    );
                }
            }           
        } 
        
        return food;
    }

    /**
     * Updates the details of an existing food item.
     * 
     * @param food The {@link Food} object containing updated data.
     * @param conn SQL connection
     * @return boolean Returns true if the food was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean updateFood(Food food, Connection conn)  throws SQLException{          
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE foods SET id = ?, name = ?, expire_days = ?, "
                            + "unitprice = ?, discount = ? WHERE id = ?")) {
            
            pstmt.setInt(1, food.getId());
            pstmt.setString(2, food.getName());
            pstmt.setInt(3, food.getExpireDays());
            pstmt.setDouble(4, food.getUnitPrice());
            pstmt.setDouble(5, food.getDiscount());
            pstmt.setInt(6, food.getId());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }

     /**
     * Removes a food item from the data source.
     * 
     * @param food The {@link Food} object to be removed.
     * @param conn SQL connection
     * @return boolean Returns true if the food was successfully removed, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean removeFood(Food food, Connection conn)  throws SQLException{   
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM foods WHERE id = ?")) {
            
            pstmt.setInt(1, food.getId());            
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }
    
}
