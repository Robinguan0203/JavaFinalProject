package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This interface defines standard operations for managing {@link ExpireInfoDTO} objects in a data source.
 * It provides methods to add, retrieve, update, and remove expiration information.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface ExpireInfoDAO {
    /**
     * Adds a new {@link ExpireInfoDTO} to the data source.
     * 
     * @param expireInfoDTO The {@link ExpireInfoDTO} object to be added.
     * @param conn The SQL connection.
     * @return {@code true} if the record was successfully added, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean addExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn)  throws SQLException;
    
    /**
     * Retrieves an {@link ExpireInfoDTO} by its ID.
     * 
     * @param expireInfoId The ID of the {@link ExpireInfoDTO} to retrieve.
     * @param conn The SQL connection.
     * @return The {@link ExpireInfoDTO} object if found, {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    ExpireInfoDTO getExpireInfoById(int expireInfoId, Connection conn) throws SQLException;
    
    
    /**
     * Retrieves a list of {@link ExpireInfoDTO} records by food ID, ordered by expiration date.
     * 
     * @param foodID The food ID of the {@link ExpireInfoDTO} to retrieve.
     * @param conn The SQL connection.
     * @return A list of {@link ExpireInfoDTO} objects if found, an empty list otherwise.
     * @throws SQLException if a database access error occurs.
     */
    ArrayList<ExpireInfoDTO> getExpireInfoByFoodId(int foodID, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a list of {@link ExpireInfoDTO} records with an expiration date later than the specified date.
     * 
     * @param expireDate The expiration date to compare.
     * @param conn The SQL connection.
     * @return A list of {@link ExpireInfoDTO} objects if found, an empty list otherwise.
     * @throws SQLException if a database access error occurs.
     */
    ArrayList<ExpireInfoDTO> getExpireInfoByExpireDate(Date expireDate, Connection conn)  throws SQLException;
    
    
    /**
     * Retrieves all {@link ExpireInfoDTO} records from the data source.
     * 
     * @param conn The SQL connection.
     * @return A list of all {@link ExpireInfoDTO} objects.
     * @throws SQLException if a database access error occurs.
     */
    ArrayList<ExpireInfoDTO> getAllExpireInfo(Connection conn)  throws SQLException;
    
    /**
     * Updates an existing {@link ExpireInfoDTO}.
     * 
     * @param expireInfoDTO The {@link ExpireInfoDTO} object containing updated data.
     * @param conn The SQL connection.
     * @return {@code true} if the record was successfully updated, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean updateExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn)  throws SQLException;
    
    /**
     * Removes an {@link ExpireInfoDTO} from the data source.
     * 
     * @param expireInfoDTO The {@link ExpireInfoDTO} object to be removed.
     * @param conn The SQL connection.
     * @return {@code true} if the record was successfully removed, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean deleteExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a summary of food surplus.
     * 
     * @param conn The SQL connection.
     * @return A {@link HashMap} where the key is the food ID and the value is an array containing surplus data.
     * @throws SQLException if a database access error occurs.
     */
    HashMap<Integer, Integer[]> getFoodSurplusSummary(Connection conn) throws SQLException;
}
