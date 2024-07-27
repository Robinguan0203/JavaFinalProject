package com.fwrp.models;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import java.sql.SQLException;

public class Food {
    
    private int id;
    
    private String name;
    private int expireDays;
    private double unitPrice;
    private double discount;   
    
    public Food(){
        
    }
    
    public Food(String name, int expireDays, 
            double unitPrice, double discount){
        this.name = name;
        this.expireDays = expireDays;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }
    
    public Food(int id, String name, int expireDays, double unitPrice, double discount){
        this.id = id;
        this.name = name;
        this.expireDays = expireDays;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(int expireDays) {
        this.expireDays = expireDays;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    public void storeFood() throws DataAlreadyExistsException, DataInsertionFailedException, SQLException, Exception{
        FoodDbService foodDbService = new FoodDbService();
        
        try {
            foodDbService.AddFood(this);
        } catch (DataAlreadyExistsException | DataInsertionFailedException | SQLException e) {
            throw e; // Rethrow the specific exceptions
        } 
    }

}
