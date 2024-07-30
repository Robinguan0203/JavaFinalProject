/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.FoodDAO;
import com.fwrp.dataaccess.dao.FoodDAOImpl;
import com.fwrp.dataaccess.dao.InventoryDAO;
import com.fwrp.dataaccess.dao.InventoryDAOImpl;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Food;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Provides methods for performing database operations related to food and inventory.
 * <p>
 * This class handles the addition, retrieval, and updating of food items and manages related inventory operations
 * using Data Access Objects (DAOs). It ensures proper transaction management and exception handling for database interactions.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class FoodDbService {
    
    /**
     * The Data Access Object (DAO) for performing food-related database operations.
     * <p>
     * This DAO provides methods to interact with the food database, including adding, retrieving,
     * and updating food items.
     * </p>
     */
    private FoodDAO foodDAO = null;
    
    /**
     * The Data Access Object (DAO) for performing inventory-related database operations.
     * <p>
     * This DAO provides methods to interact with the inventory database, including managing inventory
     * for food items.
     * </p>
     */
    private InventoryDAO inventoryDAO = null;
    
    /**
     * Constructs a {@code FoodDbService} object and initializes DAO objects for food and inventory operations.
     */
    public FoodDbService(){
        foodDAO = new FoodDAOImpl(); 
        inventoryDAO = new InventoryDAOImpl(); 
    }
    
    /**
     * Adds a new food item to the database.
     * <p>
     * Checks if the food item already exists. If not, adds the food item and corresponding inventory.
     * Performs the operations within a transaction to ensure data consistency. Rolls back the transaction
     * if any operation fails.
     * </p>
     * 
     * @param food the {@link Food} object containing food details to be added.
     * @return {@code true} if the food item was successfully added; {@code false} otherwise.
     * @throws DataAlreadyExistsException if a food item with the specified name already exists.
     * @throws DataInsertionFailedException if there is a failure in adding the food item or inventory.
     * @throws Exception if a general error occurs.
     */
    public boolean AddFood(Food food) throws DataAlreadyExistsException, DataInsertionFailedException, Exception {      
        Connection conn = null;
        
        try{
            conn = DataSource.getInstance().getConnection();
            //start transaction
            conn.setAutoCommit(false); 
            
            Food foodExist = foodDAO.getFoodByName(food.getName(), conn);
            
            if(foodExist != null){
                throw new DataAlreadyExistsException("Food " + food.getName() + " aleady exists.");
            }
            
            boolean isFoodAdded = foodDAO.addFood(food, conn); 
            if(!isFoodAdded){
                throw new DataInsertionFailedException("Failed to add food with name " + food.getName() + ".");
            } 
            
            Food storedFood = foodDAO.getFoodByName(food.getName(), conn);            
            boolean isInventoryAdded = inventoryDAO.addInventoryByFoodId(storedFood.getId(), conn);
            if(!isInventoryAdded){
                throw new DataInsertionFailedException("Failed to add inventory for food with name " + food.getName() + ".");
            }
            
            conn.commit(); // 提交事务
        } catch(SQLException e){
            if (conn != null) {
                conn.rollback(); // 回滚事务
            }   
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // 恢复自动提交
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // 如果关闭连接失败，记录异常
                }
            }
        }
        
        System.out.println("new food stored");
        return true;
    }
    
     /**
     * Retrieves a food item by its name.
     * 
     * @param name the name of the food item.
     * @return a {@link Food} object if the food item is found; {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public Food getFoodByName(String name)throws SQLException, ClassNotFoundException {
        Connection conn = DataSource.getInstance().getConnection();
        Food food = null;
        
        try{
            food = foodDAO.getFoodByName(name, conn);
        } catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // Log the exception if closing connection fails
                }
            }
        }
        
        return food;
    }
    
     /**
     * Retrieves a food item by its ID.
     * 
     * @param foodId the ID of the food item.
     * @return a {@link Food} object if the food item is found; {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public Food getFoodById(int foodId)throws SQLException, ClassNotFoundException {
        Connection conn = DataSource.getInstance().getConnection();
        Food food = null;
        
        try{
            food = foodDAO.getFoodById(foodId, conn);
        } catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // Log the exception if closing connection fails
                }
            }
        }
        
        return food;
    }
    
    /**
     * Retrieves a list of all food items.
     * 
     * @return an {@link ArrayList} of {@link Food} objects.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     * @throws SQLException if a database access error occurs.
     */
    public ArrayList<Food> getAllFoods() throws ClassNotFoundException, SQLException{
        Connection conn = DataSource.getInstance().getConnection();
        ArrayList<Food> foods = new ArrayList<>();
        try{
            foods = foodDAO.getAllFoods(conn);
        } finally{
            if (conn != null) {               
                conn.close(); 
            }
        }        
        return foods;
    }
    
    /**
     * Updates an existing food item in the database.
     * 
     * @param food the {@link Food} object containing updated food details.
     * @return {@code true} if the food item was successfully updated; {@code false} otherwise.
     * @throws DataInsertionFailedException if there is a failure in updating the food item.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public boolean updateFood(Food food) throws DataInsertionFailedException, SQLException, ClassNotFoundException{
        Connection conn = null;
        
        try{
            conn = DataSource.getInstance().getConnection();
            
            boolean isFoodUpdated = foodDAO.updateFood(food, conn); 
            if(!isFoodUpdated){
                throw new DataInsertionFailedException("Failed to update food with name " + food.getName() + ".");
            } 
        } catch(SQLException e){            
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace(); // 如果关闭连接失败，记录异常
                }
            }
        }
        
        return true;
    }
}
