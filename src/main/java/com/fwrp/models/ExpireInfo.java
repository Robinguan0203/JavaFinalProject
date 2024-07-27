package com.fwrp.models;

import java.util.Date;

public class ExpireInfo {
    
    private int id;  
    private Date expireDate;
    private int quantity;
    private boolean isSurplus;
    private Food food;

    public int getId() {
        return id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isIsSurplus() {
        return isSurplus;
    }

    public Food getFood() {
        return food;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsSurplus(boolean isSurplus) {
        this.isSurplus = isSurplus;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    
}
