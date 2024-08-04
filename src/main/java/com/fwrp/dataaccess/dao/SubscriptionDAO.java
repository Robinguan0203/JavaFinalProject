package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface defines the standard operations to be performed on Subscription model object(s).
 * It provides methods to add a subscription, retrieve all subscription methods by user ID, and delete a subscription.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public interface SubscriptionDAO{
	
	/**
     * Adds a new subscription for a user.
     * 
     * @param userId The ID of the user.
     * @param method The subscription method.
     * @param conn The SQL connection used to access the database.
     * @return boolean True if the subscription was added successfully, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean addSubscription(int userId,int method , Connection conn)  throws SQLException;
	
	/**
     * Retrieves all subscription methods for a specific user.
     * 
     * @param userId The ID of the user.
     * @param conn The SQL connection used to access the database.
     * @return List<SubscriptionDTO> A list of {@link SubscriptionDTO} objects containing the subscription methods for the specified user.
     * @throws SQLException if a database access error occurs
     */
    List<SubscriptionDTO> getAllMethodsByUserId(int userId, Connection conn) throws SQLException;
	
	/**
     * Deletes a subscription by its ID.
     * 
     * @param id The ID of the subscription to be deleted.
     * @param conn The SQL connection used to access the database.
     * @return boolean True if the subscription was deleted successfully, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean deleteSubscription(int id, Connection conn)  throws SQLException;
}
