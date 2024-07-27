package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * This interface defines the standard operations to be performed on ExpireInfo model object(s).
 * It provides methods to add, retrieve, and remove user from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface ExpireInfoDAO {
    /**
     * Adds a new expireInfo to the data source.
     * 
     * @param expireInfoDTO The user object to be added.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean addExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a expireInfo by foodID, order by expire date.
     * 
     * @param foodID The food_id of the expireInfoDTO to retrieve.
     * @param conn SQL connection
     * @return ArrayList<> Returns the user object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    ArrayList<ExpireInfoDTO> getExpireInfoByFoodId(int foodID, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a expireInfo later than designated expire date.
     * 
     * @param exprieDate The expire date of the expireInfoDTO to retrieve.
     * @param conn SQL connection
     * @return ArrayList Returns the user object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    ArrayList<ExpireInfoDTO> getExpireInfoByExpireDate(Date exprieDate, Connection conn)  throws SQLException;
    
    
    /**
     * Retrieves all expireInfo.
     * 
     * @param conn SQL connection
     * @return ArrayList Returns the ExpireInfoDTO object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    ArrayList<ExpireInfoDTO> getAllExpireInfo(Connection conn)  throws SQLException;
    
    /**
     * Update a expireInfo.
     * 
     * @param expireInfoDTO The new expireInfoDTO data to save.
     * @param conn SQL Connection
     * @return Boolean Returns true if the user was successfully updated, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean updateExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn)  throws SQLException;
    
    /**
     * Removes a expireInfo from the data source.
     * 
     * @param expireInfoDTO The ExpireInfoDTO object of the user to be removed.
     * @param conn SQL Connection
     * @return Boolean Returns true if the course was successfully removed, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean deleteExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn)  throws SQLException;
}
