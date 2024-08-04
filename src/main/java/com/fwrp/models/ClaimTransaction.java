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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a ClaimTransaction.
 * This class extends the Transaction class and includes properties and methods specific to a claim transaction.
 */
public class ClaimTransaction extends Transaction {

	/**
     * The charity associated with the claim transaction.
     */
    private Charity charity;
	
	/**
     * The claim associated with the claim transaction.
     */
    private Claim claim;
	
	/**
     * The quantity of normal items in the claim transaction.
     */
    private int qtyNormal;
	
	/**
     * The quantity of discount items in the claim transaction.
     */
    private int qtyDiscount;
	
	/**
     * The quantity of donation items in the claim transaction.
     */
    private int qtyDonation;

	/**
     * Default constructor for ClaimTransaction.
     */
    public ClaimTransaction() {

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
     * Sets the quantity of normal items.
     * 
     * @param qtyNormal the quantity of normal items
     */
    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }
    
	/**
     * Gets the quantity of discount items.
     * 
     * @return the quantity of discount items
     */
    public int getQtyDiscount() {
        return qtyNormal;
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
     * Gets the quantity of donation items.
     * 
     * @return the quantity of donation items
     */
    public int getQtyDonation() {
        return qtyDonation;
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
     * Gets the charity associated with the claim transaction.
     * 
     * @return the charity
     */
    public Charity getCharity() {
        return charity;
    }

	/**
     * Gets the claim associated with the claim transaction.
     * 
     * @return the claim
     */
    public Claim getClaim() {
        return claim;
    }

	/**
     * Sets the charity associated with the claim transaction.
     * 
     * @param charity the charity
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

	/**
     * Sets the claim associated with the claim transaction.
     * 
     * @param claim the claim
     */
    public void setClaim(Claim claim) {
        this.claim = claim;
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
    public TransactionDTO createTransactionDTO() {
        TransactionDTO dto = new TransactionDTO();
        dto.setFoodId(this.getFood().getId());
        dto.setUserId(this.getUser().getId());
        dto.setDate(this.getDate());
        dto.setClaimId(this.getClaim().getId());
        dto.setType(this.getType());
        dto.setQtyNormal(this.getQtyNormal());
        dto.setQtyDiscount(this.getQtyDiscount());
        dto.setQtyDonation(this.getQtyDonation());

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
        InventoryDbService dbService = new InventoryDbService();
        ArrayList<ExpireInfoDTO> expireInfoDTOs = dbService.getExpireInfoByFoodId(this.getFood().getId());
        int remainingQty = -this.getQtyDonation();
        ArrayList<ExpireInfoDTO> expireInfoToUpdateDTOs = new ArrayList<>();
        ArrayList<ExpireInfoDTO> expireInfoToDeleteDTOs = new ArrayList<>();
        
        for(ExpireInfoDTO dto: expireInfoDTOs){
            int currentQty = dto.getQuantity();
            
            if (currentQty > remainingQty) {
                dto.setQuantity(currentQty - remainingQty);
                expireInfoToUpdateDTOs.add(dto);
                break;
            } else{
                remainingQty -= currentQty;
                expireInfoToDeleteDTOs.add(dto);                
            }
        }

        for (ExpireInfoDTO expireInfo : expireInfoToUpdateDTOs) {
            dbService.updateExpireInfo(expireInfo);
        }

        for (ExpireInfoDTO expireInfo : expireInfoToDeleteDTOs) {
            dbService.deleteExpireInfo(expireInfo);
        }
        
    }
}

