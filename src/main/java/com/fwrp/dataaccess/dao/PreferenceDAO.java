package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.PreferenceDTO;
import com.fwrp.models.Preference;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * This interface defines the standard operations to be performed on Subscription model object(s).
 * It provides methods to add a subscription, retrieve all subscription methods by user ID, and delete a subscription.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public interface PreferenceDAO{
	
    /**
    * Adds a new preference for a user.
    * 
    * @param userId The ID of the user.
    * @param foodId The ID of the food item.
    * @param conn The SQL connection used to access the database.
    * @return boolean True if the preference was added successfully, false otherwise.
    * @throws SQLException if a database access error occurs.
    */
    boolean addPreference(int userId,int foodId , Connection conn)  throws SQLException;
	
    /**
     * Retrieves all subscription methods for a specific user.
     * 
     * @param userId The ID of the user.
     * @param conn The SQL connection used to access the database.
     * @return List&lt;SubscriptionDTO&gt; A list of {@link SubscriptionDTO} objects containing the subscription methods for the specified user.
     * @throws SQLException if a database access error occurs
     */
    List<Preference> getAllFoodIdByUserId(int userId, Connection conn) throws SQLException;
	
    /**
     * Deletes a subscription by its ID.
     * 
     * @param id The ID of the subscription to be deleted.
     * @param conn The SQL connection used to access the database.
     * @return boolean True if the subscription was deleted successfully, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean deletePreference(int id, Connection conn)  throws SQLException;
    
    /**
     * Retrieves subscription information for charities.
     * 
     * @param conn The SQL connection used to access the database.
     * @return Map<Integer, Integer> A map of charity IDs to their subscription methods.
     * @throws SQLException if a database access error occurs
     */
    Map<Integer, Integer> getCharitySubscribeInfo(Connection conn) throws SQLException;
    
    /**
     * Retrieves subscription information for consumers by food ID.
     * 
     * @param foodId The ID of the food item.
     * @param conn The SQL connection used to access the database.
     * @return Map<Integer, Integer> A map of consumer IDs to their subscription methods.
     * @throws SQLException if a database access error occurs
     */
    Map<Integer, Integer> getConsumerSubscribeInfo(int foodId,Connection conn) throws SQLException;
}
