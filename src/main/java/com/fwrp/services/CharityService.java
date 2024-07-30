/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.ClaimDbService;
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
public class CharityService {
    public HashMap<Food, Integer[]>  getAllInventoryData() throws SQLException, ClassNotFoundException{
        InventoryDbService dbService = new InventoryDbService();
        HashMap<Food, Integer[]> foodSurplusMap = dbService.getAllInventoryData();
        
        return foodSurplusMap;
    }
    
    public void storeNewClaim(int userId, int foodId, Date date, int quantity) throws DataAlreadyExistsException,DataInsertionFailedException, Exception{
        ClaimDbService dbService = new ClaimDbService();
        Claim claim = new Claim(userId, foodId, date, quantity);
        dbService.CreateClaim(claim);
    }
}
