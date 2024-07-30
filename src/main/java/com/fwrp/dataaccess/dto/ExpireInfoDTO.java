/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import java.sql.SQLException;
import java.util.Date;

/**
 * Data Transfer Object (DTO) for transferring {@link ExpireInfo} data between different layers of the application.
 * This class encapsulates information related to expired food items, including their ID, associated food item, quantity,
 * expiration date, and whether the item is surplus.
 * <p>
 * This class provides methods to get and set properties and to convert the DTO to an {@link ExpireInfo} model object.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class ExpireInfoDTO {
    /**
     * The unique identifier for the expired food record.
     */
    private int id;    
    
    /**
     * The ID of the food item associated with this record.
     */
    private int foodId;
    
    /**
     * The quantity of the food item that has expired.
     */
    private int quantity;
    
    /**
     * The expiration date of the food item.
     */
    private Date expireDate;
    
    /**
     * Indicates whether the food item is surplus.
     */
    private boolean isSurplus = false;

    /**
     * Default constructor for {@link ExpireInfoDTO}.
     */
    public ExpireInfoDTO() {
    }

    /**
     * Constructs an {@link ExpireInfoDTO} with the specified values.
     * 
     * @param id The unique identifier for the expired food record.
     * @param foodId The ID of the food item associated with this record.
     * @param quantity The quantity of the food item that has expired.
     * @param expireDate The expiration date of the food item.
     * @param isSurplus {@code true} if the food item is surplus, {@code false} otherwise.
     */
    public ExpireInfoDTO(int id, int foodId, int quantity, Date expireDate, boolean isSurplus) {
        this.id = id;
        this.foodId = foodId;
        this.quantity = quantity;
        this.expireDate = expireDate;
        this.isSurplus = isSurplus;
    }

    /**
     * Gets the unique identifier for the expired food record.
     * 
     * @return The ID of the expired food record.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the ID of the food item associated with this record.
     * 
     * @return The food ID.
     */
    public int getFoodId() {
        return foodId;
    }

    /**
     * Gets the quantity of the food item that has expired.
     * 
     * @return The quantity of the expired food item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the expiration date of the food item.
     * 
     * @return The expiration date.
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * Checks if the food item is surplus.
     * 
     * @return {@code true} if the food item is surplus, {@code false} otherwise.
     */
    public boolean isIsSurplus() {
        return isSurplus;
    }

     /**
     * Sets the unique identifier for the expired food record.
     * 
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the ID of the food item associated with this record.
     * 
     * @param foodId The food ID to set.
     */
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    /**
     * Sets the quantity of the food item that has expired.
     * 
     * @param quantity The quantity to set.
     */

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the expiration date of the food item.
     * 
     * @param expireDate The expiration date to set.
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Sets whether the food item is surplus.
     * 
     * @param isSurplus {@code true} if the food item is surplus, {@code false} otherwise.
     */
    public void setIsSurplus(boolean isSurplus) {
        this.isSurplus = isSurplus;
    }
    
    /**
     * Converts this {@link ExpireInfoDTO} to an {@link ExpireInfo} model object.
     * 
     * @return An {@link ExpireInfo} object populated with data from this DTO.
     * @throws SQLException if there is an error accessing the database while retrieving the food item.
     * @throws ClassNotFoundException if the {@link FoodDbService} class is not found.
     */
    public ExpireInfo transferToExpireInfo() throws SQLException, ClassNotFoundException{
        ExpireInfo expireInfo = new ExpireInfo();
        FoodDbService foodDbService = new FoodDbService();
        Food food = null;
        try{
            food = foodDbService.getFoodById(this.getFoodId());
        } catch (SQLException e){
            throw e;
        }
        
        
        expireInfo.setId(this.getId());
        expireInfo.setExpireDate(this.getExpireDate());
        expireInfo.setQuantity(this.getQuantity());
        expireInfo.setIsSurplus(this.isIsSurplus());
        expireInfo.setFood(food);
        
        return expireInfo;
    }
}