/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dbService.NotificationDbService;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NotificationService provides methods to interact with the notification database.
 * 
 * This class includes methods to retrieve notification methods for charities and consumers,
 * as well as to insert new notifications into the database.
 * 
 * @author Robin Guan
 */
public class NotificationService {
    
    /**
     * Retrieves the notification methods for all charities.
     * 
     * This method calls the NotificationDbService to get a map of user IDs to notification methods for charities.
     * 
     * @return Map<Integer, Integer> A map where the key is the user ID and the value is the notification method.
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the NotificationDbService class cannot be found
     */
    public Map<Integer, List<Integer>> getNotificationMethodOfCharity() throws SQLException, ClassNotFoundException {
        NotificationDbService dbService = new NotificationDbService();
        return dbService.getCharityNotificationMethod();
    }
    
    /**
     * Retrieves the notification methods for consumers by food ID.
     * 
     * This method calls the NotificationDbService to get a map of user IDs to notification methods for consumers
     * based on a specific food ID.
     * 
     * @param foodId The ID of the food item.
     * @return Map<Integer, List<Integer>> A map where the key is the user ID and the value is the notification method.
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the NotificationDbService class cannot be found
     */
    public Map<Integer, List<Integer>> getNotificationMethodOfConsumetByFoodId(int foodId) throws SQLException, ClassNotFoundException {
        NotificationDbService dbService = new NotificationDbService();
        return dbService.getConsumerNotificationMethodByFoodId(foodId);
    }
    
    /**
     * Inserts a new notification into the database.
     * 
     * This method calls the NotificationDbService to insert a new notification for a specified user and method.
     * 
     * @param userId The ID of the user.
     * @param method The method of notification.
     * @param message The notification message to be inserted.
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the NotificationDbService class cannot be found
     */
    public void insertNotification(int userId, int method, String message) throws SQLException, ClassNotFoundException{
        NotificationDbService dbService = new NotificationDbService();
        dbService.insertNotification(userId, method, message);
    }
}
