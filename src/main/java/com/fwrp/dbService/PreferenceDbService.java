package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.PreferenceDAO;
import com.fwrp.dataaccess.dao.PreferenceDAOImpl;
import com.fwrp.models.Preference;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for handling database operations related to preferences.
 * This class provides methods to add, delete, and retrieve preferences for users.
 * 
 * @author Ke Yan
 * @version 2.0
 */
public class PreferenceDbService {

    /**
     * Data Access Object for preferences.
     */
    private PreferenceDAO preferenceDAO;

    /**
     * Constructor for PreferenceDbService.
     * Initializes the PreferenceDAO implementation.
     */
    public PreferenceDbService() {
        preferenceDAO = new PreferenceDAOImpl();
    }

    /**
     * Adds a new preference for a user.
     * 
     * @param userId The ID of the user.
     * @param foodId The ID of the food item.
     * @return boolean True if the preference was successfully added, otherwise false.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public boolean addPreference(int userId, int foodId) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        try {
            conn = DataSource.getInstance().getConnection();
            return preferenceDAO.addPreference(userId, foodId, conn);
        } catch (SQLException e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * Deletes a preference by its ID.
     * 
     * @param id The ID of the preference to be deleted.
     * @return boolean True if the preference was successfully deleted, otherwise false.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public boolean deletePreference(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        try {
            conn = DataSource.getInstance().getConnection();
            return preferenceDAO.deletePreference(id, conn);
        } catch (SQLException e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * Retrieves all preferences for a specific user by their user ID.
     * 
     * @param userId The ID of the user.
     * @return {@code List<Preference>} A list of preferences associated with the specified user ID.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public List<Preference> getAllFoodIdByUserId(int userId) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        try {
            conn = DataSource.getInstance().getConnection();
            return preferenceDAO.getAllFoodIdByUserId(userId, conn);
        } catch (SQLException e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
