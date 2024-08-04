package com.fwrp.models;

import java.util.Date;


public class Order implements InventoryChange{
    private int id;
    private Consumer consumer;
    private Food food;
    private Date date;
    private int qtyDiscount;
    
    public Order(){
        
    }
    
    public Order(Consumer consumer, Food food,
            Date date, int qtyDiscount){
        this.consumer = consumer;
        this.food = food;
        this.date = date;
        this.qtyDiscount = qtyDiscount;
    }
    
    public Order(int id, Consumer consumer, Food food,
            Date date, int qtyDiscount){
        this.id = id;
        this.consumer = consumer;
        this.food = food;
        this.date = date;
        this.qtyDiscount = qtyDiscount;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Consumer getConsumer() { return consumer; }
    public void setConsumer(Consumer consumer) { this.consumer = consumer; }

    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public int getQtyDiscount() { return qtyDiscount; }
    public void setQtyDiscount(int qtyDiscount) { this.qtyDiscount = qtyDiscount; }

   public OrderTransaction createTransaction() {
        OrderTransaction transaction = null;
        Claim claim = null;
        Order order = this;
        int qtyNormal = 0;
        int qtyDonation = 0;
        int this_qtyDiscount = this.getQtyDiscount();
        
        OrderTransactionCreator creator = new OrderTransactionCreator();
        transaction = creator.createTransaction(
                this.getFood(),
                this.getConsumer(),
                order,
                claim,
                qtyNormal, 
                - this_qtyDiscount, 
                qtyDonation
        );
        
        return transaction;
    } 
}
