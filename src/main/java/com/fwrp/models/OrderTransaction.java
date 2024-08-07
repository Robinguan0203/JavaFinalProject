package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dataaccess.dto.TransactionDTO;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.exceptions.NegativeInventoryException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents an OrderTransaction.
 * This class extends the Transaction class and includes properties and methods specific to an order transaction.
 * 
 * @version 2.0
 * @author Ke Yan
 */
public class OrderTransaction extends Transaction {

    /**
     * The consumer associated with the order transaction.
     */
    private Consumer consumer;

    /**
     * The order associated with the order transaction.
     */
    private Order order;

    /**
     * The normal quantity for the order transaction.
     */
    private int qtyNormal;

    /**
     * The discount quantity for the order transaction.
     */
    private int qtyDiscount;

    /**
     * The donation quantity for the order transaction.
     */
    private int qtyDonation;

    /**
     * Default constructor.
     */
    public OrderTransaction() {

    }

    /**
     * Gets the normal quantity for the order transaction.
     * 
     * @return the normal quantity.
     */
    public int getQtyNormal() {
        return qtyNormal;
    }

    /**
     * Sets the normal quantity for the order transaction.
     * 
     * @param qtyNormal the normal quantity.
     */
    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

    /**
     * Gets the discount quantity for the order transaction.
     * 
     * @return the discount quantity.
     */
    public int getQtyDiscount() {
        return qtyDiscount;
    }

    /**
     * Sets the discount quantity for the order transaction.
     * 
     * @param qtyDiscount the discount quantity.
     */
    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

    /**
     * Gets the donation quantity for the order transaction.
     * 
     * @return the donation quantity.
     */
    public int getQtyDonation() {
        return qtyDonation;
    }

    /**
     * Sets the donation quantity for the order transaction.
     * 
     * @param qtyDonation the donation quantity.
     */
    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }

    /**
     * Gets the consumer associated with the order transaction.
     * 
     * @return the consumer.
     */
    public Consumer getConsumer() {
        return consumer;
    }

    /**
     * Gets the order associated with the order transaction.
     * 
     * @return the order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the consumer associated with the order transaction.
     * 
     * @param consumer the consumer.
     */
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    /**
     * Sets the order associated with the order transaction.
     * 
     * @param order the order.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Stores the order transaction.
     * 
     * @throws NegativeInventoryException if the inventory is negative.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the class is not found.
     */
    @Override
    public void storeTransaction() throws NegativeInventoryException, SQLException, ClassNotFoundException {
        InventoryDbService dbService = new InventoryDbService();
        dbService.addTransaction(this.createTransactionDTO());
    }

    /**
     * Creates a TransactionDTO object for the order transaction.
     * 
     * @return a new TransactionDTO object.
     */
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

    /**
     * Updates the expiration information of the order transaction.
     * 
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the class is not found.
     */
    @Override
    public void updateExpireInfo() throws SQLException, ClassNotFoundException {
        InventoryDbService dbService = new InventoryDbService();
        ArrayList<ExpireInfoDTO> expireInfoDTOs = dbService.getExpireInfoByFoodId(this.getFood().getId());
        int remainingQty = -this.getQtyDiscount();
        ArrayList<ExpireInfoDTO> expireInfoToUpdateDTOs = new ArrayList<>();
        ArrayList<ExpireInfoDTO> expireInfoToDeleteDTOs = new ArrayList<>();

        for (ExpireInfoDTO dto : expireInfoDTOs) {
            int currentQty = dto.getQuantity();

            if (currentQty > remainingQty) {
                dto.setQuantity(currentQty - remainingQty);
                expireInfoToUpdateDTOs.add(dto);
                break;
            } else {
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
