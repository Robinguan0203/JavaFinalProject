/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Food;

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
}
