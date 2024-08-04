/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.OrderDbService;
import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.dbService.NotificationDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

/**
 * Service class for handling operations related to consumer inventory and orders.
 * This class provides methods to retrieve inventory data, store new orders, delete orders, and get transactions by user ID.
 * 
 * @version 1.0
 * @since 17.0.8
 * 
 * @author Ke Yan
 */
public class ConsumerService {
	
	/**
     * Retrieves all inventory data.
     * 
     * @return HashMap<Food, Integer[]> A map containing food items and their corresponding inventory data.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public HashMap<Food, Integer[]>  getAllInventoryData() throws SQLException, ClassNotFoundException{
        InventoryDbService dbService = new InventoryDbService();
        HashMap<Food, Integer[]> foodSurplusMap = dbService.getAllInventoryData();
        
        return foodSurplusMap;
    }
    
	/**
     * Stores a new order for a given food item and consumer.
     * 
     * @param FoodId The ID of the food item.
     * @param qtyDiscount The quantity of the discount.
     * @param consumer The consumer making the order.
     * @throws NegativeInventoryException if the inventory goes negative.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws DataInsertionFailedException if the data insertion fails.
     */
    public void storeNewOrder(int FoodId, int qtyDiscount, Consumer consumer) throws NegativeInventoryException, SQLException, ClassNotFoundException, DataInsertionFailedException{
        FoodDbService foodDbService = new FoodDbService();
        Food food = foodDbService.getFoodById(FoodId);
        OrderDbService orderDbService = new OrderDbService();
        int qtyNormal = 0;
        int qtyDonation = 0;
        Order order = consumer.createInventorychange(food, qtyNormal, qtyDiscount, qtyDonation);
        int orderId = orderDbService.CreateOrder(order);
        order.setId(orderId);
        OrderTransaction transaction = order.createTransaction();
        transaction.storeTransaction();
        transaction.updateExpireInfo();
    }

	/**
     * Deletes an order by its ID.
     * 
     * @param id The ID of the order to be deleted.
     * @return int The number of rows affected by the delete operation.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public int deleteOrderById(int id) throws SQLException, ClassNotFoundException {
        OrderDbService dbService = new OrderDbService();

        return dbService.deleteOrderById(id);
    }
	
	/**
     * Retrieves transactions by user ID.
     * 
     * @param userId The ID of the user.
     * @return ArrayList<Transaction> A list of transactions associated with the specified user ID.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public ArrayList<Transaction> getTransactionsByUserId(int userId) throws SQLException, ClassNotFoundException{
        InventoryDbService dbService = new InventoryDbService();
        ArrayList<Transaction> transactions = dbService.getTransactionsByUserId(userId);

        return transactions;
    }
}
