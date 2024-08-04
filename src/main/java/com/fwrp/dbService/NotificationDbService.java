package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.NotificationDAO;
import com.fwrp.dataaccess.dao.NotificationDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;

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
     * Constructor for NotificationDbService.
     * Initializes the NotificationDAO implementation.
     */
    public NotificationDbService(){
        notificationDAO = new NotificationDAOImpl();
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
}
