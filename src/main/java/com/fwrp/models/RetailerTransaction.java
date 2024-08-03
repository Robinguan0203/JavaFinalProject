/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;


import com.fwrp.constants.OtherConstants;
import com.fwrp.constants.TransactionTypeConstant;
import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dataaccess.dto.TransactionDTO;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.exceptions.NegativeInventoryException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ke Yan
 */
public class RetailerTransaction extends Transaction{
    private int qtyNormal;
    private int qtyDiscount;
    private int qtyDonation;

    public RetailerTransaction() {
        
    }
   
    public int getQtyNormal() {
        return qtyNormal;
    }

    public int getQtyDiscount() {
        return qtyDiscount;
    }

    public int getQtyDonation() {
        return qtyDonation;
    }

    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }
    
    @Override
    public void storeTransaction() throws NegativeInventoryException, SQLException, ClassNotFoundException {
        InventoryDbService dbService = new InventoryDbService();        
        dbService.addTransaction(this.createTransactionDTO());
    }
        
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

    @Override
    public void updateExpireInfo() throws SQLException, ClassNotFoundException {
        ExpireInfoDTO newExpireInfoDTO = this.createNewExpireInfoDTO();
        if(this.getQtyNormal() > 0){
            InventoryDbService dbService = new InventoryDbService();
            dbService.addExpireInfo(newExpireInfoDTO);           
        }
    }
    
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
