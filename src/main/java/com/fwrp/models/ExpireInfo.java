package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import java.sql.SQLException;
import java.util.Date;

/**
 * Class representing expiration information for food items.
 * Contains details such as expiration date, quantity, surplus status, and associated food item.
 */
public class ExpireInfo {
    
	/**
     * Unique identifier for the expiration information.
     */
    private int id; 

	/**
     * The expiration date of the food item.
     */
    private Date expireDate;
	
	/**
     * The quantity of the food item.
     */
    private int quantity;
	
	/**
     * Indicates whether the food item is surplus.
     */
    private boolean isSurplus;
	
	/**
     * The food item associated with this expiration information.
     */
    private Food food;

	/**
     * Gets the unique identifier for the expiration information.
     * 
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }
	
	/**
     * Gets the expiration date of the food item.
     * 
     * @return the expiration date
     */
    public Date getExpireDate() {
        return expireDate;
    }
	
	/**
     * Gets the quantity of the food item.
     * 
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

	/**
     * Checks if the food item is surplus.
     * 
     * @return true if the food item is surplus, false otherwise
     */
    public boolean isIsSurplus() {
        return isSurplus;
    }

	/**
     * Gets the food item associated with this expiration information.
     * 
     * @return the food item
     */
    public Food getFood() {
        return food;
    }
	
	/**
     * Sets the unique identifier for the expiration information.
     * 
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

	/**
     * Sets the expiration date of the food item.
     * 
     * @param expireDate the expiration date to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
	
	/**
     * Sets the quantity of the food item.
     * 
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	/**
     * Sets the surplus status of the food item.
     * 
     * @param isSurplus the surplus status to set
     */
    public void setIsSurplus(boolean isSurplus) {
        this.isSurplus = isSurplus;
    }

	/**
     * Sets the food item associated with this expiration information.
     * 
     * @param food the food item to set
     */
    public void setFood(Food food) {
        this.food = food;
    }
	
	/**
     * Transfers the current ExpireInfo object to an ExpireInfoDTO object.
     * 
     * @return the ExpireInfoDTO object
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the Food class is not found
     */
    public ExpireInfoDTO transferToExpireInfoDTO() throws SQLException, ClassNotFoundException{
        ExpireInfoDTO expireInfoDTO = new ExpireInfoDTO();
        
        expireInfoDTO.setId(this.getId());
        expireInfoDTO.setExpireDate(this.getExpireDate());
        expireInfoDTO.setQuantity(this.getQuantity());
        expireInfoDTO.setIsSurplus(this.isIsSurplus());
        expireInfoDTO.setFoodId(this.getFood().getId());
        
        return expireInfoDTO;
    }
    
}
