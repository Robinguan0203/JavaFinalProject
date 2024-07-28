/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.models.Food;
import com.fwrp.models.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ke Yan
 */
public class CharityService {
    public List<Inventory> getDonationInventories() throws ClassNotFoundException, SQLException {
        InventoryDbService dbService = new InventoryDbService();
        List<Inventory> donationInventories = dbService.getDonationInventories();
        return donationInventories;
    }
}
