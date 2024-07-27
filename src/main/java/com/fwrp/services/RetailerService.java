/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DateNotExistsException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import com.fwrp.models.ManageInventoryChange;
import com.fwrp.models.Retailer;
import com.fwrp.models.RetailerTransaction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
    
    public void storeFoodExpireDays(int foodId, int expireDays) throws SQLException, ClassNotFoundException, DataInsertionFailedException{
        FoodDbService foodDbService = new FoodDbService();
        Food food = foodDbService.getFoodById(foodId);
        food.setExpireDays(expireDays);
        foodDbService.updateFood(food);
    }
    
   
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
    
    public void updateExpireDateOfExpireInfo(int expireInfoId, Date expireDate) throws SQLException, ClassNotFoundException, DateNotExistsException{
        InventoryDbService dbService = new InventoryDbService();
        ExpireInfo expireInfo = dbService.getExpireInfoById(expireInfoId);
        expireInfo.setExpireDate(expireDate);
        if(expireInfo != null){
            ExpireInfoDTO dto = expireInfo.transferToExpireInfoDTO();
        }else{
            throw new DateNotExistsException("ExpireInfo not found."); 
        }

    }

}
