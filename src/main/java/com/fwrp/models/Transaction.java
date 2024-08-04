package com.fwrp.models;

import com.fwrp.dataaccess.dto.TransactionDTO;
import com.fwrp.exceptions.NegativeInventoryException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Represents a Transaction.
 * This abstract class includes properties and methods related to a transaction.
 */
public abstract class Transaction {
	
	 /**
     * The transaction ID.
     */
    private int id;
	
	/**
     * The food item associated with the transaction.
     */
    private Food food;
	
	/**
     * The user associated with the transaction.
     */
    private User user;
	
	/**
     * The date of the transaction.
     */
    private Date date;
	
	/**
     * The type of the transaction.
     */
    private int type;

	/**
     * Gets the transaction ID.
     * 
     * @return the transaction ID.
     */
    public int getId() {
        return id;
    }
	
	/**
     * Gets the food item associated with the transaction.
     * 
     * @return the food item.
     */
    public Food getFood() {
        return food;
    }
	
	/**
     * Gets the user associated with the transaction.
     * 
     * @return the user.
     */
    public User getUser() {
        return user;
    }
	
	/**
     * Gets the date of the transaction.
     * 
     * @return the date.
     */
    public Date getDate() {
        return date;
    }
	
	/**
     * Gets the type of the transaction.
     * 
     * @return the type.
     */
    public int getType() {
        return type;
    }
	
	/**
     * Sets the transaction ID.
     * 
     * @param id the transaction ID.
     */
    public void setId(int id) {
        this.id = id;
    }
	
	/**
     * Sets the food item associated with the transaction.
     * 
     * @param food the food item.
     */
    public void setFood(Food food) {
        this.food = food;
    }
	
	/**
     * Sets the user associated with the transaction.
     * 
     * @param user the user.
     */
    public void setUser(User user) {
        this.user = user;
    }
	
	/**
     * Sets the date of the transaction.
     * 
     * @param date the date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
	
	/**
     * Sets the type of the transaction.
     * 
     * @param type the type.
     */
    public void setType(int type) {
        this.type = type;
    }
	
	/**
     * Creates a TransactionDTO object.
     * 
     * @return a new TransactionDTO object.
     */
    public abstract TransactionDTO createTransactionDTO();
	
	/**
     * Stores the transaction.
     * 
     * @throws NegativeInventoryException if the inventory is negative.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the class is not found.
     */
    public abstract void storeTransaction()throws NegativeInventoryException, SQLException,ClassNotFoundException;
	
	/**
     * Updates the expiration information of the transaction.
     * 
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the class is not found.
     */
    public abstract void updateExpireInfo() throws SQLException,ClassNotFoundException;
    
}
