/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.models.Consumer;
import com.fwrp.models.Food;
import com.fwrp.models.Order;
import java.util.Date;

/**
 *
 * @author YAOZHOU XIE
 */
public class OrderDTO {

    private int id;
    private int quantity;
    private Date date;
    private double unitPrice;
    private double discount;
    private int foodId;
    private int consumerId;

    public OrderDTO() {
    }

    public OrderDTO(int id, int quantity, Date date, double unitPrice, double discount, int foodId, int consumerId) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.foodId = foodId;
        this.consumerId = consumerId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public Order toOrder(Food food, Consumer consumer) {
        return new Order(this.id, this.quantity, this.date, this.unitPrice, this.discount, food, consumer);
    }
    
}

