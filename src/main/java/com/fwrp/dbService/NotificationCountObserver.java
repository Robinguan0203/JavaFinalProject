/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.models.User;
import java.sql.SQLException;

/**
 * Interface for observing notification counts.
 * Implementations of this interface provide methods to retrieve the count of notifications for a given user.
 * 
 * @version 1.0
 * @since 17.0.8
 * 
 * @author Robin Guan
 */
public interface NotificationCountObserver {
	
	/**
     * Retrieves the count of notifications for a given user.
     * 
     * @param user The user for whom the notification count is to be retrieved.
     * @return int The count of notifications for the specified user.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    int getNotificationCount(User user) throws SQLException, ClassNotFoundException;
}
