/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.Food;
import com.fwrp.models.ManageInventoryChange;
import com.fwrp.models.ManageInventoryChangeCreator;
import com.fwrp.models.Retailer;
import com.fwrp.models.RetailerTransaction;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ke Yan
 */
public class RetailerService {
    public void storeNewFood(String name, int expireDays, double unitPrice, double discount) throws DataAlreadyExistsException,DataInsertionFailedException, Exception{
        FoodDbService dbService = new FoodDbService();
        Food food = new Food(name, expireDays, unitPrice, discount);
        dbService.AddFood(food);
    }
    
    public ArrayList<Food> getAllFoods() throws ClassNotFoundException, SQLException{
        FoodDbService dbService = new FoodDbService();
        ArrayList<Food> foods = dbService.getAllFoods();
        return foods;
    }
    
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

}
