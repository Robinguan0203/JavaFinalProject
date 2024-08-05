package com.fwrp.dataaccess.dao;

import com.fwrp.models.Food;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * This interface defines the standard operations to be performed on Food model object(s).
 * It provides methods to add, retrieve, update, and remove food items from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface FoodDAO {
    /**
     * Adds a new food item to the data source.
     * 
     * @param food The Food object to be added.
     * @param conn SQL connection
     * @return boolean Returns true if the food was successfully added, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean addFood(Food food, Connection conn)  throws SQLException;
    
    /**
     * Retrieves all food items from the data source.
     * 
     * @param conn SQL connection
     * @return ArrayList&lt;Food&gt; A list of Food objects.
     * @throws SQLException if a database access error occurs
     */
    ArrayList<Food> getAllFoods(Connection conn)  throws SQLException;
    
    /**
     * Retrieves a food item by its ID.
     * 
     * @param foodId The ID of the food to retrieve.
     * @param conn SQL connection
     * @return Food Returns the Food object if found, null otherwise.
     * @throws SQLException if a database access error occurs
     */
    Food getFoodById(int foodId, Connection conn)  throws SQLException;
    
     /**
     * Retrieves a food item by its name.
     * 
     * @param name The name of the food to retrieve.
     * @param conn SQL connection
     * @return Food Returns the Food object if found, null otherwise.
     * @throws SQLException if a database access error occurs
     */
    Food getFoodByName(String name, Connection conn)  throws SQLException;
    
    /**
     * Updates the details of an existing food item.
     * 
     * @param food The Food object containing updated data.
     * @param conn SQL connection
     * @return boolean Returns true if the food was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean updateFood(Food food, Connection conn)  throws SQLException;
    
    /**
     * Removes a food item from the data source.
     * 
     * @param food The Food object to be removed.
     * @param conn SQL connection
     * @return boolean Returns true if the food was successfully removed, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean removeFood(Food food, Connection conn)  throws SQLException;
}
