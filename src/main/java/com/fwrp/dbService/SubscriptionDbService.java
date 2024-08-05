package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.SubscriptionDAO;
import com.fwrp.dataaccess.dao.SubscriptionDAOImpl;
import com.fwrp.dataaccess.dto.SubscriptionDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for handling database operations related to subscriptions.
 * This class provides methods to add, delete, and retrieve subscription methods for users.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public class SubscriptionDbService {
	
	/**
     * Data Access Object for subscriptions.
     */
    private SubscriptionDAO subscriptionDAO;
	
	/**
     * Constructor for SubscriptionDbService.
     * Initializes the SubscriptionDAO implementation.
     */
    public SubscriptionDbService(){
        subscriptionDAO = new SubscriptionDAOImpl();
    }

	/**
     * Adds a new subscription for a user.
     * 
     * @param userId The ID of the user.
     * @param method The subscription method.
     * @return boolean True if the subscription was successfully added, otherwise false.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public boolean addSubscription(int userId,int method) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.addSubscription(userId,method, conn);
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
    }

	/**
     * Deletes a subscription by its ID.
     * 
     * @param id The ID of the subscription to be deleted.
     * @return boolean True if the subscription was successfully deleted, otherwise false.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public boolean deleteSubscription(int id) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.deleteSubscription(id, conn);
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
    }

	/**
     * Retrieves all subscription methods for a specific user by their user ID.
     * 
     * @param userId The ID of the user.
     * @return {@code List<SubscriptionDTO>} A list of subscription methods associated with the specified user ID.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public List<SubscriptionDTO> getAllMethodsByUserId(int userId) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.getAllMethodsByUserId(userId, conn);
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
    }
}
