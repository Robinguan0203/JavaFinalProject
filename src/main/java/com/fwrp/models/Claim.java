package com.fwrp.models;

import java.util.Date;

/**
 * Represents a Claim.
 * This class implements the InventoryChange interface and includes properties and methods related to a claim.
 */
public class Claim implements InventoryChange{
	
	/**
     * The claim ID.
     */
    private int id;
	
	/**
     * The charity associated with the claim.
     */
    private Charity charity;
	
	/**
     * The food item associated with the claim.
     */
    private Food food;
	
	/**
     * The date of the claim.
     */
    private Date date;
	
	/**
     * The quantity donation for the claim.
     */
    private int qtyDonation;
    
	/**
     * Default constructor.
     */
    public Claim(){
        
    }
    
	/**
     * Constructs a Claim with the specified charity, food, date, and quantity donation.
     * 
     * @param charity the charity associated with the claim.
     * @param food the food item associated with the claim.
     * @param date the date of the claim.
     * @param qtyDonation the quantity donation for the claim.
     */
    public Claim(Charity charity, Food food,
            Date date, int qtyDonation){
        this.charity = charity;
        this.food = food;
        this.date = date;
        this.qtyDonation = qtyDonation;
    }
    
	/**
     * Constructs a Claim with the specified ID, charity, food, date, and quantity donation.
     * 
     * @param id the claim ID.
     * @param charity the charity associated with the claim.
     * @param food the food item associated with the claim.
     * @param date the date of the claim.
     * @param qtyDonation the quantity donation for the claim.
     */
    public Claim(int id, Charity charity, Food food,
            Date date, int qtyDonation){
        this.id = id;
        this.charity = charity;
        this.food = food;
        this.date = date;
        this.qtyDonation = qtyDonation;
    }

    /**
     * Gets the claim ID.
     * 
     * @return the claim ID.
     */
    public int getId() { return id; }
	
	/**
     * Sets the claim ID.
     * 
     * @param id the claim ID.
     */
    public void setId(int id) { this.id = id; }

	/**
     * Gets the charity associated with the claim.
     * 
     * @return the charity.
     */
    public Charity getCharity() { return charity; }
	
	/**
     * Sets the charity associated with the claim.
     * 
     * @param charity the charity.
     */
    public void setCharity(Charity charity) { this.charity = charity; }

	/**
     * Gets the food item associated with the claim.
     * 
     * @return the food item.
     */
    public Food getFood() { return food; }
	
	/**
     * Sets the food item associated with the claim.
     * 
     * @param food the food item.
     */
    public void setFood(Food food) { this.food = food; }

	/**
     * Gets the date of the claim.
     * 
     * @return the date.
     */
    public Date getDate() { return date; }
	
	/**
     * Sets the date of the claim.
     * 
     * @param date the date.
     */
    public void setDate(Date date) { this.date = date; }

	/**
     * Gets the quantity donation for the claim.
     * 
     * @return the quantity donation.
     */
    public int getQtyDonation() { return qtyDonation; }
	
	/**
     * Sets the quantity donation for the claim.
     * 
     * @param qtyDonation the quantity donation.
     */
    public void setQtyDonation(int qtyDonation) { this.qtyDonation = qtyDonation; }

	/**
     * Creates a transaction for the claim.
     * 
     * @return a new ClaimTransaction object.
     */
	public ClaimTransaction createTransaction() {
        ClaimTransaction transaction = null;
        Order order = null;
        Claim claim = this;
        int qtyNormal = 0;
        int qtyDiscount = 0;
        int this_qtyDonation = this.getQtyDonation();
        
        ClaimTransactionCreator creator = new ClaimTransactionCreator();
        transaction = creator.createTransaction(
                this.getFood(),
                this.getCharity(),
                order,
                claim,
                qtyNormal, 
                qtyDiscount, 
                - this_qtyDonation
        );
        
        return transaction;
    } 
}
