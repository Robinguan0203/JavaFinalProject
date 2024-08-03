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
 * @author Siqian
 */
public class ClaimTransaction extends Transaction {

    private Charity charity;
    private Claim claim;
    private int qtyNormal;
    private int qtyDiscount;
    private int qtyDonation;

    public ClaimTransaction() {

    }

    public int getQtyNormal() {
        return qtyNormal;
    }

    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }
    
    public int getQtyDiscount() {
        return qtyNormal;
    }

    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }
    
    public int getQtyDonation() {
        return qtyDonation;
    }

    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }

    public Charity getCharity() {
        return charity;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setRetailer(Charity charity) {
        this.charity = charity;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
    

    @Override
    public void storeTransaction() throws NegativeInventoryException, SQLException, ClassNotFoundException {
        InventoryDbService dbService = new InventoryDbService();
        dbService.addTransaction(this.createTransactionDTO());
    }
    

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

