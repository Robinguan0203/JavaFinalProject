package com.fwrp.models;

/**
 * Represents an inventory item with properties such as food, quantities of normal, discounted, and donated items.
 */
public class Inventory {
	
	/**
     * Unique identifier for the inventory item.
     */
    private int id;
	
	/**
     * The food item associated with the inventory.
     */
    private Food food;
	
	/**
     * Quantity of normal items in the inventory.
     */
    private int qtyNormal;
	
	/**
     * Quantity of discounted items in the inventory.
     */
    private int qtyDiscount;
	
	/**
     * Quantity of donated items in the inventory.
     */
    private int qtyDonation;

	/**
     * Default constructor.
     */
    public Inventory() {
    }

	/**
     * Constructor to initialize an inventory item with specified properties.
     * 
     * @param id the unique identifier for the inventory item
     * @param food the food item associated with the inventory
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     */
    public Inventory(int id, Food food, int qtyNormal, int qtyDiscount, int qtyDonation) {
        this.id = id;
        this.food = food;
        this.qtyNormal = qtyNormal;
        this.qtyDiscount = qtyDiscount;
        this.qtyDonation = qtyDonation;
    }

	/**
     * Gets the unique identifier for the inventory item.
     * 
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }
	
	/**
     * Gets the food item associated with the inventory.
     * 
     * @return the food item
     */
    public Food getFood() {
        return food;
    }
	
	/**
     * Gets the quantity of normal items in the inventory.
     * 
     * @return the quantity of normal items
     */
    public int getQtyNormal() {
        return qtyNormal;
    }
	
	/**
     * Gets the quantity of discounted items in the inventory.
     * 
     * @return the quantity of discounted items
     */
    public int getQtyDiscount() {
        return qtyDiscount;
    }
	
	/**
     * Gets the quantity of donated items in the inventory.
     * 
     * @return the quantity of donated items
     */
    public int getQtyDonation() {
        return qtyDonation;
    }

	/**
     * Sets the unique identifier for the inventory item.
     * 
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

	/**
     * Sets the food item associated with the inventory.
     * 
     * @param food the food item to set
     */
    public void setFood(Food food) {
        this.food = food;
    }
	
	/**
     * Sets the quantity of normal items in the inventory.
     * 
     * @param qtyNormal the quantity of normal items to set
     */
    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

	/**
     * Sets the quantity of discounted items in the inventory.
     * 
     * @param qtyDiscount the quantity of discounted items to set
     */
    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }
	
	/**
     * Sets the quantity of donated items in the inventory.
     * 
     * @param qtyDonation the quantity of donated items to set
     */
    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }
    
    
}
