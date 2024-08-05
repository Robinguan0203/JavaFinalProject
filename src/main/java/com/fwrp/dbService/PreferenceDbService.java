package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.PreferenceDAO;
import com.fwrp.dataaccess.dao.PreferenceDAOImpl;
import com.fwrp.dataaccess.dto.PreferenceDTO;
import com.fwrp.models.Preference;

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
public class PreferenceDbService {
	
	/**
     * Data Access Object for subscriptions.
     */
    private PreferenceDAO preferenceDAO;
	
	/**
     * Constructor for SubscriptionDbService.
     * Initializes the SubscriptionDAO implementation.
     */
    public PreferenceDbService(){
        preferenceDAO = new PreferenceDAOImpl();
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
    public boolean addPreference(int userId,int foodId) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return preferenceDAO.addPreference(userId,foodId, conn);
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
    public boolean deletePreference(int id) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return preferenceDAO.deletePreference(id, conn);
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
     * @return List<SubscriptionDTO> A list of subscription methods associated with the specified user ID.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public List<Preference> getAllFoodIdByUserId(int userId) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return preferenceDAO.getAllFoodIdByUserId(userId, conn);
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
