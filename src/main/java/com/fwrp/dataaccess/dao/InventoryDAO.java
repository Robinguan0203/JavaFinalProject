package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.InventoryDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;



/**
 * This interface defines the standard operations to be performed on Inventory model object(s).
 * It provides methods to add, retrieve, and remove user from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface InventoryDAO {
    /**
     * Adds a new inventory to the data source.
     * 
     * @param foodId The Inventory of the food to be added.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean addInventoryByFoodId(int foodId, Connection conn)  throws SQLException ;
    
    /**
     * Retrieves a inventory by food ID.
     * 
     * @param foodId The ID of the food to retrieve.
     * @param conn SQL connection
     * @return Inventory Returns the Inventory object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    InventoryDTO getInventoryByFoodId(int foodId, Connection conn)  throws SQLException ;
    
    /**
     * Update a inventory.
     * 
     * @param inventoryDTO The new inventory data to save.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully updated, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean updateInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException ;
    
    /**
     * Removes a user from the data source.
     * 
     * @param inventoryDTO The Inventory object of the inventory to be removed.
     * @param conn SQL connection
     * @return Boolean Returns true if the course was successfully removed, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean removeInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException ;
    
    HashMap<Integer, Integer[]> getAllInventoryData(Connection conn) throws SQLException;
}
