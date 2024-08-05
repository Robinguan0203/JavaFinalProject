/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;


import com.fwrp.constants.TransactionTypeConstant;
import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dataaccess.dto.TransactionDTO;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.exceptions.NegativeInventoryException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a RetailerTransaction.
 * This class extends the Transaction class and includes properties and methods specific to a retailer transaction.
 */
public class RetailerTransaction extends Transaction{
	
	/**
     * The quantity of normal items in the retailer transaction.
     */
    private int qtyNormal;
	
	/**
     * The quantity of discount items in the retailer transaction.
     */
    private int qtyDiscount;
	
	/**
     * The quantity of donation items in the retailer transaction.
     */
    private int qtyDonation;

	/**
     * Default constructor for RetailerTransaction.
     */
    public RetailerTransaction() {
        
    }
   
	/**
     * Gets the quantity of normal items.
     * 
     * @return the quantity of normal items
     */
    public int getQtyNormal() {
        return qtyNormal;
    }

	/**
     * Gets the quantity of discount items.
     * 
     * @return the quantity of discount items
     */
    public int getQtyDiscount() {
        return qtyDiscount;
    }

	/**
     * Gets the quantity of donation items.
     * 
     * @return the quantity of donation items
     */
    public int getQtyDonation() {
        return qtyDonation;
    }
	
	/**
     * Sets the quantity of normal items.
     * 
     * @param qtyNormal the quantity of normal items
     */
    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

	/**
     * Sets the quantity of discount items.
     * 
     * @param qtyDiscount the quantity of discount items
     */
    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

	/**
     * Sets the quantity of donation items.
     * 
     * @param qtyDonation the quantity of donation items
     */
    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }
    
	/**
     * Stores the transaction in the database.
     * 
     * @throws NegativeInventoryException if the inventory goes negative
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class is not found
     */
    @Override
    public void storeTransaction() throws NegativeInventoryException, SQLException, ClassNotFoundException {
        InventoryDbService dbService = new InventoryDbService();        
        dbService.addTransaction(this.createTransactionDTO());
    }
        
	/**
     * Creates a TransactionDTO object from the current transaction.
     * 
     * @return the created TransactionDTO
     */
    @Override
    public TransactionDTO createTransactionDTO(){
        TransactionDTO dto = new TransactionDTO();
        dto.setFoodId(this.getFood().getId());
        dto.setUserId(this.getUser().getId());
        dto.setDate(this.getDate());
        dto.setType(TransactionTypeConstant.INVENTORY_CHANGE);
        dto.setQtyNormal(this.qtyNormal);
        dto.setQtyDiscount(this.qtyDiscount);
        dto.setQtyDonation(this.qtyDonation);
        
        return dto;
    }

	/**
     * Updates the expiration information in the database.
     * 
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class is not found
     */
    @Override
    public void updateExpireInfo() throws SQLException, ClassNotFoundException {
        ExpireInfoDTO newExpireInfoDTO = this.createNewExpireInfoDTO();
        if(this.getQtyNormal() > 0){
            InventoryDbService dbService = new InventoryDbService();
            dbService.addExpireInfo(newExpireInfoDTO);           
        }
    }
    
	/**
     * Creates a new ExpireInfoDTO object.
     * 
     * @return the created ExpireInfoDTO
     */
    public ExpireInfoDTO createNewExpireInfoDTO(){
        Date currentDate = new Date();
        
        // 使用 Calendar 类来增加过期天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, this.getFood().getExpireDays());
        
        // 获取过期日期
        Date expireDate = calendar.getTime();        
        
        ExpireInfoDTO newExpireInfoDTO = new ExpireInfoDTO();
        newExpireInfoDTO.setFoodId(this.getFood().getId());
        newExpireInfoDTO.setQuantity(this.getQtyNormal());
        newExpireInfoDTO.setExpireDate(expireDate);
        newExpireInfoDTO.setIsSurplus(false);
                
        return newExpireInfoDTO;
        
    }
    
}
