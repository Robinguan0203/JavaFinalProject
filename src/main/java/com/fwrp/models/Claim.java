package com.fwrp.models;

import java.sql.Date;

public class Claim {
    private int id;
    private int userId;
    private int foodId;
    private Date date;
    private int quantity;
    
    public Claim(){
        
    }
    
    public Claim(int userId, int foodId, 
            Date date, int quantity){
        this.userId = userId;
        this.foodId = foodId;
        this.date = date;
        this.quantity = quantity;
    }
    
    public Claim(int id, int userId, int foodId, 
            Date date, int quantity){
        this.id = id;
        this.userId = userId;
        this.foodId = foodId;
        this.date = date;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
