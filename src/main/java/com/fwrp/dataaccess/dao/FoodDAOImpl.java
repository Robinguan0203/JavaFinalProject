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
 * This class implements the FoodDAO interface to provide a concrete implementation.
 * It provides methods to add, retrieve, and remove users from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class FoodDAOImpl implements FoodDAO {

    /**
     * Adds a new food to the data source.
     * 
     * @param food The Food object to be added.
     * @param conn SQL Connection
     * @return Boolean Returns true if the course was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
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
            pstmt.setInt(1, food.getId());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }

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
