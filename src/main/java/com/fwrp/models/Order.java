package com.fwrp.models;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author YAOZHOU XIE
 */
public class Order {
    
    private int id;
    private int quantity;

    private Date date;
    
    private double unitPrice;
    private double discount;

   
    private Food food;

    
    private Consumer consumer;
    
    public Order() {
        
    }
    
    public Order(int id, int quantity, Date date, double unitPrice, double discount, Food food, Consumer consumer) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.food = food;
        this.consumer = consumer;
    }
    
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

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Food getFood() {
        return food;
    }
    
    public int getFoodId() {
        return food.getId();
    }
    public void setFood(Food food) {
        this.food = food;
    }

    public Consumer getConsumer() {
        return consumer;
    }
    
    public int getConsumerId() {
        return consumer.getId();
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
