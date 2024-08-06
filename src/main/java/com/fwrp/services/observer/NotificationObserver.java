/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.services.observer;

import java.sql.SQLException;

/**
 * NotificationObserver is an interface for observing notifications.
 * 
 * Implementations of this interface are responsible for handling updates when a new notification message is received.
 * The update method is called with the notification message, and it may throw SQLException or ClassNotFoundException.
 * 
 * @author robin
 */
public interface NotificationObserver {
    
    /**
     * Updates the observer with a new notification message.
     * 
     * This method is called when a new notification message is received. Implementations should handle
     * the notification appropriately, such as inserting it into a database or displaying it to the user.
     * 
     * @param message The notification message to be handled.
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if a required class cannot be found
     */
    void update(String message) throws SQLException, ClassNotFoundException;
}
