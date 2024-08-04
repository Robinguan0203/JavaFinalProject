/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.ClaimDbService;
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
 * Service class for handling operations related to charity inventory and claims.
 * This class provides methods to retrieve inventory data, store new claims, delete claims, and get transactions by user ID.
 * 
 * @version 1.0
 * @since 17.0.8
 * 
 * @author Ke Yan
 */
public class CharityService {
	
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
     * Stores a new claim for a given food item and charity.
     * 
     * @param FoodId The ID of the food item.
     * @param qtyDonation The quantity of the donation.
     * @param charity The charity making the claim.
     * @throws NegativeInventoryException if the inventory goes negative.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws DataInsertionFailedException if the data insertion fails.
     */
    public void storeNewClaim(int FoodId, int qtyDonation, Charity charity) throws NegativeInventoryException, SQLException, ClassNotFoundException, DataInsertionFailedException{
        FoodDbService foodDbService = new FoodDbService();
        Food food = foodDbService.getFoodById(FoodId);
        ClaimDbService claimDbService = new ClaimDbService();
        int qtyNormal = 0;
        int qtyDiscount = 0;
        Claim claim = charity.createInventorychange(food, qtyNormal, qtyDiscount, qtyDonation);
        int claimId = claimDbService.CreateClaim(claim);
        claim.setId(claimId);
        ClaimTransaction transaction = claim.createTransaction();
        transaction.storeTransaction();
        transaction.updateExpireInfo();
    }
    
	/**
     * Deletes a claim by its ID.
     * 
     * @param id The ID of the claim to be deleted.
     * @return int The number of rows affected by the delete operation.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public int deleteClaimById(int id) throws SQLException, ClassNotFoundException {
        ClaimDbService dbService = new ClaimDbService();

        return dbService.deleteClaimById(id);
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
