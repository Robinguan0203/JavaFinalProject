package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.NotificationDAO;
import com.fwrp.dataaccess.dao.NotificationDAOImpl;
import com.fwrp.dataaccess.dao.SubscriptionDAO;
import com.fwrp.dataaccess.dao.SubscriptionDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for handling database operations related to notifications.
 * This class provides methods to send notifications using the NotificationDAO.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public class NotificationDbService {
	
    /**
     * Data Access Object for notifications.
     */
    private NotificationDAO notificationDAO;
    
    /**
     * Data Access Object for subscriptions.
     */
    private SubscriptionDAO subscriptionDAO;
	
    /**
     * Constructor for NotificationDbService.
     * Initializes the NotificationDAO implementation.
     */
    public NotificationDbService(){
        notificationDAO = new NotificationDAOImpl();
        subscriptionDAO = new SubscriptionDAOImpl();
    }

    /**
     * Sends a notification.
     * 
     * @param notification The notification message to be sent.
     * @return boolean True if the notification was successfully sent, otherwise false.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public boolean sendNotification(String notification) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return notificationDAO.sendNotifications(notification, conn);
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
     * Retrieves the notification methods for charities.
     * 
     * @return Map<Integer, Integer> A map of charity IDs to their notification methods.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public Map<Integer, List<Integer>> getCharityNotificationMethod()throws SQLException, ClassNotFoundException{
        Connection conn = null;
        
        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.getCharitySubscribeInfo(conn);
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
     * Retrieves the notification methods for consumers by food ID.
     * 
     * @param foodId The ID of the food item.
     * @return Map<Integer, List<Integer>> A map of consumer IDs to their notification methods.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public Map<Integer, List<Integer>> getConsumerNotificationMethodByFoodId(int foodId)throws SQLException, ClassNotFoundException{
        Connection conn = null;
        
        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.getConsumerSubscribeInfo(foodId,conn);
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
     * Inserts a notification into the database.
     * 
     * @param userId The ID of the user to be notified.
     * @param method The notification method.
     * @param notification The notification message.
     * @return boolean True if the notification was successfully inserted, otherwise false.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public boolean insertNotification(int userId, int method, String notification) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            notificationDAO.insertNotification(userId, method, notification, conn);
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
        
        return true;
    }
}
