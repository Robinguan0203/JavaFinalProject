package com.fwrp.dataaccess.dao;

import com.fwrp.models.Food;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * This interface defines the standard operations to be performed on Food model object(s).
 * It provides methods to add, retrieve, and remove food from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface FoodDAO {
    /**
     * Adds a new food to the data source.
     * 
     * @param food The food object to be added.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean addFood(Food food, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a food by its ID.
     * 
     * @param foodId The ID of the food to retrieve.
     * @param conn SQL connection
     * @return Food Returns the user object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    Food getFoodById(int foodId, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a food by its name.
     * 
     * @param name The name of the food to retrieve.
     * @param conn SQL connection
     * @return Food Returns the user object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    Food getFoodByName(String name, Connection conn)  throws SQLException;
    
    /**
     * Update a food.
     * 
     * @param food The new food data to save.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully updated, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean updateFood(Food food, Connection conn)  throws SQLException;
    
    /**
     * Removes a food from the data source.
     * 
     * @param food The Food object of the user to be removed.
     * @param conn SQL connection
     * @return Boolean Returns true if the course was successfully removed, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean removeFood(Food food, Connection conn)  throws SQLException;
}
