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
 * @author Ke Yan
 */
public class OrderTransaction extends Transaction {

    private Consumer consumer;
    private Order order;
    private int qtyNormal;
    private int qtyDiscount;
    private int qtyDonation;

    public OrderTransaction() {

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

    public Consumer getConsumer() {
        return consumer;
    }

    public Order getOrder() {
        return order;
    }

    public void setRetailer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void setOrder(Order order) {
        this.order = order;
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
        dto.setOrderId(this.getOrder().getId());
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
        int remainingQty = -this.getQtyDiscount();
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
