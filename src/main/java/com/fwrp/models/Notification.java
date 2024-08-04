package com.fwrp.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Class representing a notification.
 * This class contains information about the notification method, date, content, and the user associated with the notification.
 * 
 * The class provides getters and setters for accessing and modifying the properties.
 * 
 */
public class Notification {

	/**
     * The unique identifier for the notification.
     */
    private int id;
	
	/**
     * The method of notification (e.g., email, SMS).
     */
    private int method;
    
	/**
     * The date of the notification.
     */
    private Date date;
    
	/**
     * The content of the notification.
     */
    private String notification;

	/**
     * The user associated with the notification.
     */
    private User user;

    /**
     * Gets the unique identifier for the notification.
     * 
     * @return the unique identifier for the notification
     */
    public int getId() {
        return id;
    }
	
	/**
     * Sets the unique identifier for the notification.
     * 
     * @param id the unique identifier for the notification
     */
    public void setId(int id) {
        this.id = id;
    }

	/**
     * Gets the method of notification.
     * 
     * @return the method of notification
     */
    public int getMethod() {
        return method;
    }

	/**
     * Sets the method of notification.
     * 
     * @param method the method of notification
     */
    public void setMethod(int method) {
        this.method = method;
    }
	
	/**
     * Gets the date of the notification.
     * 
     * @return the date of the notification
     */
    public Date getDate() {
        return date;
    }
	
	/**
     * Sets the date of the notification.
     * 
     * @param date the date of the notification
     */
    public void setDate(Date date) {
        this.date = date;
    }
	
	/**
     * Gets the content of the notification.
     * 
     * @return the content of the notification
     */
    public String getNotification() {
        return notification;
    }
	
	/**
     * Sets the content of the notification.
     * 
     * @param notification the content of the notification
     */
    public void setNotification(String notification) {
        this.notification = notification;
    }
	
	/**
     * Gets the user associated with the notification.
     * 
     * @return the user associated with the notification
     */
    public User getUser() {
        return user;
    }
	
	/**
     * Sets the user associated with the notification.
     * 
     * @param user the user associated with the notification
     */
    public void setUser(User user) {
        this.user = user;
    }
}
