package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.SubscriptionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * @return List<SubscriptionDTO> A list of {@link SubscriptionDTO} objects containing the subscription methods for the specified user.
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

}
