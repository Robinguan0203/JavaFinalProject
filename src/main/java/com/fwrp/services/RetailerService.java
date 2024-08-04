/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.dbService.NotificationDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Service class for retailer-related operations.
 * This class provides methods to manage food items, inventory, and notifications for retailers.
 * 
 * <p>
 * Example use case: Storing new food items, adding food quantities, managing expire info, and sending notifications.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class RetailerService {
    /**
     * Stores a new food item in the database.
     * 
     * @param name the name of the food
     * @param expireDays the number of days until the food expires
     * @param unitPrice the unit price of the food
     * @param discount the discount rate of the food
     * @throws DataAlreadyExistsException if the food already exists in the database
     * @throws DataInsertionFailedException if the insertion fails
     * @throws Exception if any other exception occurs
     */
    public void storeNewFood(String name, int expireDays, double unitPrice, double discount) throws DataAlreadyExistsException,DataInsertionFailedException, Exception{
        FoodDbService dbService = new FoodDbService();
        Food food = new Food(name, expireDays, unitPrice, discount);
        dbService.AddFood(food);
    }
    
    /**
    * Retrieves a list of all food items from the database.
    * <p>
    * This method creates an instance of {@link FoodDbService} to access the database and fetch all food items.
    * The method then returns an {@link ArrayList} of {@link Food} objects representing the food items retrieved.
    * </p>
    * 
    * @return an {@link ArrayList} containing all food items retrieved from the database
    * @throws ClassNotFoundException if the database driver class is not found
    * @throws SQLException if a database access error occurs or this method is called on a closed connection
    */
    public ArrayList<Food> getAllFoods() throws ClassNotFoundException, SQLException{
        FoodDbService dbService = new FoodDbService();
        ArrayList<Food> foods = dbService.getAllFoods();
        return foods;
    }
    
    /**
     * Adds quantities of a food item to the inventory.
     * 
     * @param FoodId the ID of the food
     * @param quantity the quantity to add
     * @param retailer the retailer managing the inventory change
     * @throws NegativeInventoryException if the inventory becomes negative
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public void addFoodQuantities(int FoodId, int quantity, Retailer retailer) throws NegativeInventoryException, SQLException, ClassNotFoundException{
        FoodDbService foodDbService = new FoodDbService();
        Food food = foodDbService.getFoodById(FoodId);
      
        int qtyNormal = quantity;
        int qtyDiscount = 0;
        int qtyDonation = 0;
        
        ManageInventoryChange qtyChange = retailer.createInventorychange(food, qtyNormal, qtyDiscount, qtyDonation);

        RetailerTransaction transaction = qtyChange.createTransaction();
        transaction.storeTransaction();
        transaction.updateExpireInfo();
    }
    
    /**
     * Updates the expiration days of a food item.
     * 
     * @param foodId the ID of the food
     * @param expireDays the new number of expiration days
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     * @throws DataInsertionFailedException if the update fails
     */
    public void storeFoodExpireDays(int foodId, int expireDays) throws SQLException, ClassNotFoundException, DataInsertionFailedException{
        FoodDbService foodDbService = new FoodDbService();
        Food food = foodDbService.getFoodById(foodId);
        food.setExpireDays(expireDays);
        foodDbService.updateFood(food);
    }
    
   /**
     * Retrieves all expiration information items from the database.
     * 
     * @return a list of all expiration information items
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public ArrayList<ExpireInfo> getAllExpireInfoItems() throws SQLException, ClassNotFoundException {
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();        
        InventoryDbService dbService = new InventoryDbService();
        
        ArrayList<ExpireInfoDTO> expireDTOs = dbService.queryAllExpireInfo();
        
        if(!expireDTOs.isEmpty()){
            for(ExpireInfoDTO dto : expireDTOs){
                expireInfos.add(dto.transferToExpireInfo());
            }
        } 
        
        return expireInfos;
    }
    
    /**
     * Retrieves expiration information items that are close to expiring.
     * 
     * @return a list of expiration information items close to expiring
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public ArrayList<ExpireInfo> getExpireInfoClosedToExpireItems() throws SQLException, ClassNotFoundException {
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();        
        InventoryDbService dbService = new InventoryDbService();
        
        ArrayList<ExpireInfoDTO> expireDTOs = dbService.queryExpireInfoClosedToExpire();
        
        if(!expireDTOs.isEmpty()){
            for(ExpireInfoDTO dto : expireDTOs){
                expireInfos.add(dto.transferToExpireInfo());
            }
        } 
        
        return expireInfos;
    }
    
    /**
     * Updates the expiration date of an expiration information item.
     * 
     * @param expireInfoId the ID of the expiration information item
     * @param expireDate the new expiration date
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     * @throws DataNotExistsException if the expiration information item does not exist
     */
    public void updateExpireDateOfExpireInfo(int expireInfoId, Date expireDate) throws SQLException, ClassNotFoundException, DataNotExistsException{
        InventoryDbService dbService = new InventoryDbService();
        ExpireInfo expireInfo = dbService.getExpireInfoById(expireInfoId);
        
        if(expireInfo == null){
            throw new DataNotExistsException("ExpireInfo not found.");
        }else{
            expireInfo.setExpireDate(expireDate);
            ExpireInfoDTO dto = expireInfo.transferToExpireInfoDTO(); 
            dbService.updateExpireInfo(dto);
        }

    }
    
    /**
     * Updates the surplus status of an expiration information item.
     * 
     * @param expireInfoId the ID of the expiration information item
     * @param isSurplus the new surplus status
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     * @throws DataNotExistsException if the expiration information item does not exist
     */
    public void updateIsSurplusOfExpireInfo(int expireInfoId, boolean isSurplus) throws SQLException, ClassNotFoundException, DataNotExistsException{
        InventoryDbService dbService = new InventoryDbService();
        ExpireInfo expireInfo = dbService.getExpireInfoById(expireInfoId);
        if(expireInfo == null){
            throw new DataNotExistsException("ExpireInfo not found.");
        }else{
            expireInfo.setIsSurplus(isSurplus);
            ExpireInfoDTO dto = expireInfo.transferToExpireInfoDTO(); 
            dbService.updateExpireInfo(dto);
        }
    }

    /**
     * Retrieves a summary of surplus food items.
     * 
     * @return a map of food items and their surplus quantities
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public HashMap<Food, Integer[]> getFoodSurplusSummary() throws SQLException, ClassNotFoundException{
        InventoryDbService dbService = new InventoryDbService();
        HashMap<Food, Integer[]> foodSurplusMap = dbService.getFoodSurplusSummary();
        
        return foodSurplusMap;
    }
    
    /**
     * Lists surplus food items.
     * 
     * @param FoodId the ID of the food
     * @param qtyDiscount the quantity to discount
     * @param qtyDonation the quantity to donate
     * @param retailer the retailer managing the inventory change
     * @throws NegativeInventoryException if the inventory becomes negative
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public void listSurplusFood(int FoodId, int qtyDiscount,int qtyDonation, Retailer retailer) throws NegativeInventoryException, SQLException, ClassNotFoundException{
        FoodDbService foodDbService = new FoodDbService();
        Food food = foodDbService.getFoodById(FoodId);
        int qtyNormal = - (qtyDiscount + qtyDonation);
       
        ManageInventoryChange qtyChange = retailer.createInventorychange(food, qtyNormal, qtyDiscount, qtyDonation);
        sendNotifications("Food is "+food.getName()+" and discount quantity is "+qtyDiscount+" and donation quantity is "+qtyDonation);
        RetailerTransaction transaction = qtyChange.createTransaction();
        transaction.storeTransaction();
    }
    
    /**
     * Retrieves all inventory data.
     * 
     * @return a map of food items and their quantities
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public HashMap<Food, Integer[]>  getAllInventoryData() throws SQLException, ClassNotFoundException{
        InventoryDbService dbService = new InventoryDbService();
        HashMap<Food, Integer[]> foodSurplusMap = dbService.getAllInventoryData();
        
        return foodSurplusMap;
    }

    /**
     * Sends notifications.
     * 
     * @param notification the notification message
     * @return true if the notification was sent successfully, false otherwise
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public boolean sendNotifications(String notification) throws SQLException, ClassNotFoundException {
        NotificationDbService dbService = new NotificationDbService();
        return dbService.sendNotification(notification);
    }
    
    /**
	 * Retrieves all transactions.
	 * 
	 * @return ArrayList<Transaction> A list of all transactions.
	 * @throws SQLException if a database access error occurs or the SQL query fails.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
    public ArrayList<Transaction> getAllTransactions() throws SQLException, ClassNotFoundException{        
        InventoryDbService dbService = new InventoryDbService();
        ArrayList<Transaction> transactions = dbService.getTransactions();
        
        return transactions;
    }
    
}
