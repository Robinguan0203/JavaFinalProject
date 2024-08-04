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
 *
 * @author Ke Yan
 */
public class ConsumerService {
    public HashMap<Food, Integer[]>  getAllInventoryData() throws SQLException, ClassNotFoundException{
        InventoryDbService dbService = new InventoryDbService();
        HashMap<Food, Integer[]> foodSurplusMap = dbService.getAllInventoryData();
        
        return foodSurplusMap;
    }
    
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
    /*
    public void storeNewOrder(int userId, Food food, Date date, int quantity) throws DataAlreadyExistsException,DataInsertionFailedException, Exception{
        OrderDbService dbService = new OrderDbService();
        Order order = new Order(userId, food, date, quantity);

        dbService.CreateOrder(order);
    }
    */

    public int deleteOrderById(int id) throws SQLException, ClassNotFoundException {
        OrderDbService dbService = new OrderDbService();

        return dbService.deleteOrderById(id);
    }

    public ArrayList<Transaction> getTransactionsByUserId(int userId) throws SQLException, ClassNotFoundException{
        InventoryDbService dbService = new InventoryDbService();
        ArrayList<Transaction> transactions = dbService.getTransactionsByUserId(userId);

        return transactions;
    }
}
