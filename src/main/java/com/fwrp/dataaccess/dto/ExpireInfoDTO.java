/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author robin
 */
public class ExpireInfoDTO {
    private int id;    
    private int foodId;
    private int quantity;
    private Date expireDate;
    private boolean isSurplus = false;

    public ExpireInfoDTO() {
    }

    public ExpireInfoDTO(int id, int foodId, int quantity, Date expireDate, boolean isSurplus) {
        this.id = id;
        this.foodId = foodId;
        this.quantity = quantity;
        this.expireDate = expireDate;
        this.isSurplus = isSurplus;
    }

    public int getId() {
        return id;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public boolean isIsSurplus() {
        return isSurplus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setIsSurplus(boolean isSurplus) {
        this.isSurplus = isSurplus;
    }
    
    public ExpireInfo transferToExpireInfo() throws SQLException, ClassNotFoundException{
        ExpireInfo expireInfo = new ExpireInfo();
        FoodDbService foodDbService = new FoodDbService();
        Food food = null;
        try{
            food = foodDbService.getFoodById(this.getFoodId());
        } catch (SQLException e){
            throw e;
        }
        
        
        expireInfo.setId(this.getId());
        expireInfo.setExpireDate(this.getExpireDate());
        expireInfo.setQuantity(this.getQuantity());
        expireInfo.setIsSurplus(this.isIsSurplus());
        expireInfo.setFood(food);
        
        return expireInfo;
    }
}