package com.fwrp.dataaccess.dao;

import com.fwrp.constants.UserTypeConstant;
import com.fwrp.dataaccess.dto.SubscriptionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the SubscriptionDAO interface.
 * Provides methods to add a subscription, retrieve all subscription methods by user ID, and delete a subscription.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public class SubscriptionDAOImpl implements SubscriptionDAO {
    
    /**
     * Adds a new subscription for a user.
     * 
     * @param userId The ID of the user.
     * @param method The subscription method.
     * @param conn The SQL connection used to access the database.
     * @return boolean True if the subscription was added successfully, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean addSubscription(int userId,int method , Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("insert into subscriptions(user_id,method) values(?,?)")) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, method);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    /**
     * Retrieves all subscription methods for a specific user.
     * 
     * @param userId The ID of the user.
     * @param conn The SQL connection used to access the database.
     * @return List&lt;SubscriptionDTO&gt; A list of {@link SubscriptionDTO} objects containing the subscription methods for the specified user.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<SubscriptionDTO> getAllMethodsByUserId(int userId, Connection conn) throws SQLException {
        List<SubscriptionDTO> subscriptionDTOS = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM subscriptions "
                + "WHERE user_id = ?")){

            pstmt.setInt(1, userId);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
                    subscriptionDTO.setId(rs.getInt("id"));
                    subscriptionDTO.setUserId(rs.getInt("user_id"));
                    subscriptionDTO.setMethod(rs.getInt("method"));

                    subscriptionDTOS.add(subscriptionDTO);
                }
            }
        }

        return subscriptionDTOS;
    }

    /**
     * Deletes a subscription by its ID.
     * 
     * @param id The ID of the subscription to be deleted.
     * @param conn The SQL connection used to access the database.
     * @return boolean True if the subscription was deleted successfully, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean deleteSubscription(int id, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("delete from subscriptions where id=?")) {

            pstmt.setInt(1, id);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }
    
    /**
     * Retrieves the subscription methods for charities.
     * 
     * @param conn The SQL connection used to access the database.
     * @return Map&lt;Integer, List&lt;Integer&gt;&gt; A map of charity IDs to their subscription methods.
     * @throws SQLException if a database access error occurs
     */
    public Map<Integer, List<Integer>> getCharitySubscribeInfo(Connection conn) throws SQLException {
        Map<Integer, List<Integer>> map = new HashMap<>();

        try (PreparedStatement pstmt = conn.prepareStatement("SELECT user_id, method "
                + "FROM subscriptions as s "
                + "LEFT JOIN users ON s.user_id = users.id "
                + "WHERE type = " + UserTypeConstant.CHARITY)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    int method = rs.getInt("method");

                    // If the userId is not in the map, create a new ArrayList for this userId
                    if (!map.containsKey(userId)) {
                        map.put(userId, new ArrayList<>());
                    }
                    // Add the method to the ArrayList for this userId
                    map.get(userId).add(method);
                }
            }
        }

        return map;
    }
    
    /**
     * Retrieves the subscription methods for consumers by food ID.
     * 
     * @param foodId The ID of the food item.
     * @param conn The SQL connection used to access the database.
     * @return Map&lt;Integer, List&lt;Integer&gt;&gt; A map of consumer IDs to their subscription methods.
     * @throws SQLException if a database access error occurs
     */
    public Map<Integer, List<Integer>> getConsumerSubscribeInfo(int foodId,Connection conn) throws SQLException{
        Map<Integer, List<Integer>> map = new HashMap<>();
         try(PreparedStatement pstmt = conn.prepareStatement("SELECT s.user_id, method "
                 + "FROM subscriptions as s "
                 + "LEFT JOIN users on s.user_id = users.id "
                 + "LEFT JOIN preferences as p on s.user_id = p.user_id "
                 + "WHERE food_id = ? "
                 + "AND type = " + UserTypeConstant.CONSUMER)){
            
            pstmt.setInt(1, foodId);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    int method = rs.getInt("method");
                    
                    // If the userId is not in the map, create a new ArrayList for this userId
                    if (!map.containsKey(userId)) {
                        map.put(userId, new ArrayList<>());
                    }
                    // Add the method to the ArrayList for this userId
                    map.get(userId).add(method);
                }
            }
            
        }
            
        return map;
    }
}
