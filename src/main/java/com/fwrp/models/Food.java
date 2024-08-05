package com.fwrp.models;

/**
 * Represents a food item with properties such as name, expiration days, unit price, and discount.
 */
public class Food {
    
	/**
     * Unique identifier for the food item.
     */
    private int id;
    
	/**
     * Name of the food item.
     */
    private String name;
	
	/**
     * Number of days before the food item expires.
     */
    private int expireDays;
	
	/**
     * Unit price of the food item.
     */
    private double unitPrice;
	
	 /**
     * Discount applied to the food item.
     */
    private double discount;   
    
	/**
     * Default constructor.
     */
    public Food(){
        
    }
    
	/**
     * Constructor to initialize a food item with specified properties.
     * 
     * @param name the name of the food item
     * @param expireDays the number of days before the food item expires
     * @param unitPrice the unit price of the food item
     * @param discount the discount applied to the food item
     */
    public Food(String name, int expireDays, 
            double unitPrice, double discount){
        this.name = name;
        this.expireDays = expireDays;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }
    
	/**
     * Constructor to initialize a food item with specified properties including ID.
     * 
     * @param id the unique identifier for the food item
     * @param name the name of the food item
     * @param expireDays the number of days before the food item expires
     * @param unitPrice the unit price of the food item
     * @param discount the discount applied to the food item
     */
    public Food(int id, String name, int expireDays, double unitPrice, double discount){
        this.id = id;
        this.name = name;
        this.expireDays = expireDays;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    /**
     * Gets the unique identifier for the food item.
     * 
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }
	
	/**
     * Sets the unique identifier for the food item.
     * 
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

	/**
     * Gets the name of the food item.
     * 
     * @return the name of the food item
     */
    public String getName() {
        return name;
    }
	
	/**
     * Sets the name of the food item.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
     * Gets the number of days before the food item expires.
     * 
     * @return the number of expiration days
     */
    public int getExpireDays() {
        return expireDays;
    }
	
	/**
     * Sets the number of days before the food item expires.
     * 
     * @param expireDays the number of expiration days to set
     */
    public void setExpireDays(int expireDays) {
        this.expireDays = expireDays;
    }
	
	/**
     * Gets the unit price of the food item.
     * 
     * @return the unit price
     */
    public double getUnitPrice() {
        return unitPrice;
    }
	
	/**
     * Sets the unit price of the food item.
     * 
     * @param unitPrice the unit price to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
	
	/**
     * Gets the discount applied to the food item.
     * 
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }
	
	/**
     * Sets the discount applied to the food item.
     * 
     * @param discount the discount to set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
}
