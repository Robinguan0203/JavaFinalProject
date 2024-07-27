/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import java.util.Date;

/**
 *
 * @author robin
 */
public class TransactionDTO {
    private int id;    
    private int foodId;
    private int userId;
    private Integer  orderId = null;
    private Integer claimId = null;
    private Date date;
    private int type;
    private int qtyNormal = 0;
    private int qtyDiscount = 0;
    private int qtyDonation = 0;

    public TransactionDTO(){
        
    }

    public int getId() {
        return id;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getUserId() {
        return userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getClaimId() {
        return claimId;
    }

    public Date getDate() {
        return date;
    }

    public int getType() {
        return type;
    }

    public int getQtyNormal() {
        return qtyNormal;
    }

    public int getQtyDiscount() {
        return qtyDiscount;
    }

    public int getQtyDonation() {
        return qtyDonation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }
    
    
}
