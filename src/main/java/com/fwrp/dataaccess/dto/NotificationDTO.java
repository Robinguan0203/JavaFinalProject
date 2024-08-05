/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dbService.UserDbService;
import com.fwrp.models.Notification;
import com.fwrp.models.User;
import java.sql.SQLException;
import java.util.Date;

/**
 * Data Transfer Object (DTO) for managing notifications.
 * This class encapsulates the details of a notification, including its ID, method, date,
 * content, and the user associated with the notification.
 * <p>
 * This class provides methods to get and set properties for managing notification data,
 * as well as a method to convert the DTO into a {@link Notification} model object.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class NotificationDTO {    
    /**
     * The unique identifier for the notification.
     */
    private int id;
    
    /**
     * The method used to deliver the notification (e.g., email, SMS).
     */
    private int method;
    
    /**
     * The date when the notification was created or sent.
     */
    private Date date;
    
     /**
     * The content or message of the notification.
     */
    private String notification;
    
    /**
     * The ID of the user associated with the notification.
     */
    private int userId;

    /**
     * Gets the unique identifier for the notification.
     * 
     * @return The ID of the notification.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the method used to deliver the notification.
     * 
     * @return The delivery method.
     */
    public int getMethod() {
        return method;
    }

    /**
     * Gets the date when the notification was created or sent.
     * 
     * @return The date of the notification.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the content or message of the notification.
     * 
     * @return The notification content.
     */
    public String getNotification() {
        return notification;
    }

    /**
     * Gets the ID of the user associated with the notification.
     * 
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the notification.
     * 
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

     /**
     * Sets the method used to deliver the notification.
     * 
     * @param method The delivery method to set.
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * Sets the date when the notification was created or sent.
     * 
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets the content or message of the notification.
     * 
     * @param notification The notification content to set.
     */
    public void setNotification(String notification) {
        this.notification = notification;
    }

    /**
     * Sets the ID of the user associated with the notification.
     * 
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Converts this DTO into a {@link Notification} model object.
     * <p>
     * This method retrieves the {@link User} object associated with the notification
     * using the {@link UserDbService} and sets the relevant properties on a new
     * {@link Notification} object.
     * </p>
     * 
     * @return A {@link Notification} object with the data from this DTO.
     * @throws SQLException If an SQL error occurs during retrieval of user data.
     * @throws ClassNotFoundException If the {@link UserDbService} class cannot be found.
     */
    public Notification transferToNotification() throws SQLException, ClassNotFoundException{
        Notification notification = new Notification();
        UserDbService userDbService = new UserDbService();
        User user = null;
        try{
            user = userDbService.getUserById(this.getUserId());
        } catch (SQLException e){
            throw e;
        }
        
        notification.setId(this.getId());
        notification.setUser(user);
        notification.setMethod(this.getMethod());
        notification.setDate(this.getDate());
        notification.setNotification(this.getNotification());
                
        return notification;
    }
    
}
