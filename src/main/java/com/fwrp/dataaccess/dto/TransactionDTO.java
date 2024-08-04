/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.constants.UserTypeConstant;
import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.UserDbService;
import com.fwrp.models.*;

import java.sql.SQLException;
import java.util.Date;

/**
 * Data Transfer Object (DTO) for managing transaction data.
 * This class encapsulates the details of a transaction, including its ID, food ID,
 * user ID, optional order and claim IDs, date, type, and quantities of normal, discount,
 * and donation items.
 * <p>
 * This class provides methods to get and set properties for managing transaction data.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class TransactionDTO {
    /**
     * The unique identifier for the transaction.
     */
    private int id;    
    
    /**
     * The ID of the food involved in the transaction.
     */
    private int foodId;
    
     /**
     * The ID of the associated order, if applicable.
     * This field is nullable.
     */
    private int userId;
    
     /**
     * The ID of the associated claim, if applicable.
     * This field is nullable.
     */
    private Integer  orderId = null;
    
     /**
     * The ID of the associated claim, if applicable.
     * This field is nullable.
     */
    private Integer claimId = null;
    
    
    /**
     * The date when the transaction occurred.
     */
    private Date date;
    
    /**
     * The type of transaction (e.g., order, claim).
     */
    private int type;
    
    /**
     * The quantity of normal items involved in the transaction.
     */
    private int qtyNormal = 0;
    
    /**
     * The quantity of discount items involved in the transaction.
     */
    private int qtyDiscount = 0;
    
    /**
     * The quantity of donation items involved in the transaction.
     */
    private int qtyDonation = 0;
    
    /**
     * Default constructor.
     */
    public TransactionDTO(){
        
    }

    /**
     * Gets the unique identifier for the transaction.
     * 
     * @return The ID of the transaction.
     */
    public int getId() {
        return id;
    }

     /**
     * Gets the ID of the food involved in the transaction.
     * 
     * @return The food ID.
     */
    public int getFoodId() {
        return foodId;
    }

    /**
     * Gets the ID of the user who initiated the transaction.
     * 
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the ID of the associated order, if applicable.
     * 
     * @return The order ID, or null if not applicable.
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Gets the ID of the associated claim, if applicable.
     * 
     * @return The claim ID, or null if not applicable.
     */
    public Integer getClaimId() {
        return claimId;
    }

    /**
     * Gets the date when the transaction occurred.
     * 
     * @return The date of the transaction.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the type of transaction (e.g., order, claim).
     * 
     * @return The transaction type.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the quantity of normal items involved in the transaction.
     * 
     * @return The quantity of normal items.
     */
    public int getQtyNormal() {
        return qtyNormal;
    }

    /**
     * Gets the quantity of discount items involved in the transaction.
     * 
     * @return The quantity of discount items.
     */
    public int getQtyDiscount() {
        return qtyDiscount;
    }

    /**
     * Gets the quantity of donation items involved in the transaction.
     * 
     * @return The quantity of donation items.
     */
    public int getQtyDonation() {
        return qtyDonation;
    }

    /**
     * Sets the unique identifier for the transaction.
     * 
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

     /**
     * Sets the ID of the food involved in the transaction.
     * 
     * @param foodId The food ID to set.
     */
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    /**
     * Sets the ID of the user who initiated the transaction.
     * 
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the ID of the associated order.
     * 
     * @param orderId The order ID to set, or null if not applicable.
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * Sets the ID of the associated claim.
     * 
     * @param claimId The claim ID to set, or null if not applicable.
     */
    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    /**
     * Sets the date when the transaction occurred.
     * 
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

     /**
     * Sets the type of transaction.
     * 
     * @param type The transaction type to set.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Sets the quantity of normal items involved in the transaction.
     * 
     * @param qtyNormal The quantity to set.
     */
    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

    /**
     * Sets the quantity of discount items involved in the transaction.
     * 
     * @param qtyDiscount The quantity to set.
     */
    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

     /**
     * Sets the quantity of donation items involved in the transaction.
     * 
     * @param qtyDonation The quantity to set.
     */
    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }
    
	/**
	 * Transfers the current object to a Transaction object based on the user type.
	 * This method retrieves the necessary food and user information, and then creates
	 * a transaction based on the type of user (Charity, Consumer, or Retailer).
	 * 
	 * @return Transaction The created transaction object.
	 * @throws ClassNotFoundException if the user type is invalid.
	 * @throws SQLException if a database access error occurs or the SQL query fails.
	 */
    public Transaction transferToTransaction() throws ClassNotFoundException, SQLException{
        Transaction transaction = null;
        FoodDbService foodDbService = new FoodDbService();
        Food food = foodDbService.getFoodById(this.getFoodId());
        UserDbService userDBService = new UserDbService();
        User user = userDBService.getUserById(this.getUserId());
        ManageInventoryChange manageInventoryChange=null;
        Claim claim = null;
        Order order = null;
        switch(user.getType()){
            case UserTypeConstant.CHARITY:
                Charity charity = (Charity) user;
                claim =
                    charity.createInventorychange(
                            food,
                            this.getQtyNormal(),
                            this.getQtyDiscount(),
                            - this.getQtyDonation());
                
                transaction = claim.createTransaction();
                transaction.setDate(date);
                break;
            case UserTypeConstant.CONSUMER:
                Consumer consumer = (Consumer) user;
                order = consumer.createInventorychange(
                            food,
                            this.getQtyNormal(),
                            - this.getQtyDiscount(),
                            this.getQtyDonation());  
                transaction = order.createTransaction();
                transaction.setDate(date);
                break;
            case UserTypeConstant.RETAILER:
                Retailer retailer = (Retailer) user;
                manageInventoryChange =
                    retailer.createInventorychange(
                        food, 
                        this.getQtyNormal(), 
                        this.getQtyDiscount(), 
                        this.getQtyDonation());
                transaction = manageInventoryChange.createTransaction();
                transaction.setDate(date);
                break;
            default:
                throw new ClassNotFoundException("Wrong User Type!");
        }
        
        
        return transaction;      

    }
}
