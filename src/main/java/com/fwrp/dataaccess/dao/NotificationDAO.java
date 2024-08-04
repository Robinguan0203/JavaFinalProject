package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.NotificationDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This interface defines the standard operations to be performed on Notification model object(s).
 * It provides methods to send notifications, retrieve notifications by user ID and method, 
 * and get the count of notifications by user ID and method.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public interface NotificationDAO{
	
	/**
     * Sends a notification.
     * 
     * @param notification The notification message to be sent.
     * @param conn SQL connection
     * @return boolean Returns true if the notification was successfully sent, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    boolean sendNotifications(String notification ,Connection conn)  throws SQLException;
	
	/**
     * Retrieves notifications by user ID and method.
     * 
     * @param userId The ID of the user to retrieve notifications for.
     * @param method The method of notification.
     * @param conn SQL connection
     * @return ArrayList<NotificationDTO> A list of {@link NotificationDTO} objects.
     * @throws SQLException if a database access error occurs
     */
    ArrayList<NotificationDTO> getNotificationByUserIdAndMethod(int userId, int method, Connection conn) throws SQLException;
	
	/**
     * Gets the count of notifications by user ID and method.
     * 
     * @param userId The ID of the user to count notifications for.
     * @param method The method of notification.
     * @param conn SQL connection
     * @return int The count of notifications.
     * @throws SQLException if a database access error occurs
     */
    int getNotifictionCountByUserIdAndMethod(int userId, int method, Connection conn) throws SQLException;
}
