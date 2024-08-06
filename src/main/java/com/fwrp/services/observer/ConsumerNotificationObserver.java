/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services.observer;

import com.fwrp.services.NotificationService;
import java.sql.SQLException;

/**
 * ConsumerNotificationObserver is an implementation of the NotificationObserver interface.
 * 
 * This class is responsible for observing notifications for a specific consumer user and method.
 * When an update is received, it uses the NotificationService to insert the notification into the database.
 * 
 * @author Robin Guan
 */
public class ConsumerNotificationObserver implements NotificationObserver{
    private int userId;
    private int method;

    /**
     * Constructs a new ConsumerNotificationObserver with the specified user ID and method.
     * 
     * @param userId The ID of the user.
     * @param method The method of notification.
     */
    public ConsumerNotificationObserver(int userId, int method) {
        this.userId = userId;
        this.method = method;
    }

    /**
     * Updates the observer with a new notification message.
     * 
     * This method is called when a new notification message is received. It uses the NotificationService
     * to insert the notification into the database for the specified user and method.
     * 
     * @param message The notification message to be inserted.
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the NotificationService class cannot be found
     */
    @Override
    public void update(String message) throws SQLException, ClassNotFoundException {
        NotificationService service = new NotificationService();
        service.insertNotification(userId, method, message);
    }
}
