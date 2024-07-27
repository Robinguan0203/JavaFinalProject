/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

/**
 *
 * @author robin
 */
public class InventoryDTO {
    private int id;    
    private int foodId;
    private int qtyNormal;
    private int qtyDiscount;
    private int qtydonation;

    public InventoryDTO() {
    }

    public InventoryDTO(int id, int foodId, int qtyNormal, int qtyDiscount, int qtydonation) {
        this.id = id;
        this.foodId = foodId;
        this.qtyNormal = qtyNormal;
        this.qtyDiscount = qtyDiscount;
        this.qtydonation = qtydonation;
    }

    public int getId() {
        return id;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getQtyNormal() {
        return qtyNormal;
    }

    public int getQtyDiscount() {
        return qtyDiscount;
    }

    public int getQtydonation() {
        return qtydonation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

    public void setQtydonation(int qtydonation) {
        this.qtydonation = qtydonation;
    }
    
    
}
