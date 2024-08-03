package com.fwrp.models;

import java.sql.Date;

public class Claim {
    private int id;
    private int userId;
    private Food food;
    private Date date;
    private int quantity;
    
    public Claim(){
        
    }
    
    public Claim(int userId, Food food,
            Date date, int quantity){
        this.userId = userId;
        this.food = food;
        this.date = date;
        this.quantity = quantity;
    }
    
    public Claim(int id, int userId, Food food,
            Date date, int quantity){
        this.id = id;
        this.userId = userId;
        this.food = food;
        this.date = date;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
