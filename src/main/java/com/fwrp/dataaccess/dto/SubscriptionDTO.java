/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;


/**
 * This class represents a Data Transfer Object (DTO) for a subscription.
 * It contains information about the subscription such as the ID, user ID, and method.
 * 
 * @version 1.0
 * @since 17.0.8
 * 
 * @author Siqian
 */
public class SubscriptionDTO {
	
	/**
	 * The unique identifier for the subscription
	*/
    private int id;
	
	/**
	 * The unique identifier for the user
	*/
    private int userId;
	
	/**
	 * The method of the subscription
	*/
    private int method;

	/**
     * Gets the unique identifier for the subscription.
     * 
     * @return int The subscription ID.
     */
    public int getId() {
        return id;
    }
	
	/**
     * Gets the unique identifier for the user.
     * 
     * @return int The user ID.
     */
    public int getUserId() {
        return userId;
    }
	
	/**
     * Gets the method of the subscription.
     * 
     * @return int The subscription method.
     */
    public int getMethod() {
        return method;
    }

	/**
     * Sets the unique identifier for the subscription.
     * 
     * @param id The subscription ID.
     */
    public void setId(int id) {
        this.id = id;
    }

	/**
     * Sets the method of the subscription.
     * 
     * @param method The subscription method.
     */
    public void setMethod(int method) {
        this.method = method;
    }
	
	/**
     * Sets the unique identifier for the user.
     * 
     * @param userId The user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

	/**
     * Default constructor for the SubscriptionDTO class.
     */
    public SubscriptionDTO() {

    }
}
