/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

/**
 * Data Transfer Object (DTO) for managing inventory information related to food items.
 * This class encapsulates details about food inventory, including its ID, associated food item,
 * quantities of normal, discounted, and donated items.
 * <p>
 * This class provides methods to get and set properties for managing inventory data.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class InventoryDTO {
    
    /**
     * The unique identifier for the inventory record.
     */
    private int id;    
    
    /**
     * The ID of the food item associated with this inventory record.
     */
    private int foodId;
    
    /**
     * The quantity of the food item available at the normal price.
     */
    private int qtyNormal;
    
     /**
     * The quantity of the food item available at a discounted price.
     */
    private int qtyDiscount;
    
     /**
     * The quantity of the food item that is donated.
     */
    private int qtydonation;

    /**
     * Default constructor for {@link InventoryDTO}.
     */
    public InventoryDTO() {
    }

    /**
     * Constructs an {@link InventoryDTO} with the specified values.
     * 
     * @param id The unique identifier for the inventory record.
     * @param foodId The ID of the food item associated with this inventory record.
     * @param qtyNormal The quantity of the food item available at the normal price.
     * @param qtyDiscount The quantity of the food item available at a discounted price.
     * @param qtydonation The quantity of the food item that is donated.
     */
    public InventoryDTO(int id, int foodId, int qtyNormal, int qtyDiscount, int qtydonation) {
        this.id = id;
        this.foodId = foodId;
        this.qtyNormal = qtyNormal;
        this.qtyDiscount = qtyDiscount;
        this.qtydonation = qtydonation;
    }

    /**
     * Gets the unique identifier for the inventory record.
     * 
     * @return The ID of the inventory record.
     */
    public int getId() {
        return id;
    }

     /**
     * Gets the ID of the food item associated with this inventory record.
     * 
     * @return The food ID.
     */
    public int getFoodId() {
        return foodId;
    }

    /**
     * Gets the quantity of the food item available at the normal price.
     * 
     * @return The quantity of normal items.
     */
    public int getQtyNormal() {
        return qtyNormal;
    }

     /**
     * Gets the quantity of the food item available at a discounted price.
     * 
     * @return The quantity of discounted items.
     */
    public int getQtyDiscount() {
        return qtyDiscount;
    }

    /**
     * Gets the quantity of the food item that is donated.
     * 
     * @return The quantity of donated items.
     */
    public int getQtydonation() {
        return qtydonation;
    }

    /**
     * Sets the unique identifier for the inventory record.
     * 
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

     /**
     * Sets the ID of the food item associated with this inventory record.
     * 
     * @param foodId The food ID to set.
     */
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    /**
     * Sets the quantity of the food item available at the normal price.
     * 
     * @param qtyNormal The quantity of normal items to set.
     */
    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

    /**
     * Sets the quantity of the food item available at a discounted price.
     * 
     * @param qtyDiscount The quantity of discounted items to set.
     */
    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

    /**
     * Sets the quantity of the food item that is donated.
     * 
     * @param qtydonation The quantity of donated items to set.
     */
    public void setQtydonation(int qtydonation) {
        this.qtydonation = qtydonation;
    }
    
    
}
