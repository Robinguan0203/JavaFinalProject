package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.InventoryDTO;
import com.fwrp.models.Inventory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;



/**
 * This interface defines the standard operations to be performed on Inventory model object(s).
 * It provides methods to add, retrieve, update, and remove inventory data from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface InventoryDAO {
    /**
     * Adds a new inventory entry for a specific food item to the data source.
     * 
     * @param foodId The ID of the food item for which inventory is being added.
     * @param conn SQL connection
     * @return boolean Returns true if the inventory was successfully added, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean addInventoryByFoodId(int foodId, Connection conn)  throws SQLException ;

    /**
     * Retrieves inventory information for a specific food item by its ID.
     * 
     * @param foodId The ID of the food item to retrieve inventory for.
     * @param conn SQL connection
     * @return InventoryDTO Returns the {@link InventoryDTO} object if found, null otherwise.
     * @throws SQLException if a database access error occurs
     */
    InventoryDTO getInventoryByFoodId(int foodId, Connection conn)  throws SQLException ;

    /**
     * Updates the inventory data for a specific food item.
     * 
     * @param inventoryDTO The {@link InventoryDTO} object containing updated inventory data.
     * @param conn SQL connection
     * @return boolean Returns true if the inventory was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean updateInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException ;

    /**
     * Removes the inventory entry for a specific food item from the data source.
     * 
     * @param inventoryDTO The {@link InventoryDTO} object representing the inventory to be removed.
     * @param conn SQL connection
     * @return boolean Returns true if the inventory was successfully removed, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean removeInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException ;

    /**
     * Retrieves a list of all donation inventories.
     * 
     * @param conn SQL connection
     * @return List<Inventory> A list of {@link Inventory} objects representing donation inventories.
     * @throws SQLException if a database access error occurs
     */
    List<Inventory> getDonationInventories(Connection conn) throws SQLException ;

    /**
     * Retrieves all inventory data, including quantities for each item.
     * 
     * @param conn SQL connection
     * @return HashMap<Integer, Integer[]> A map where the key is the food ID and the value is an array containing inventory data.
     * @throws SQLException if a database access error occurs
     */
    HashMap<Integer, Integer[]> getAllInventoryData(Connection conn) throws SQLException;
}
