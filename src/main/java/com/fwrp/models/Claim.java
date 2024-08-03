package com.fwrp.models;

import java.util.Date;


public class Claim implements InventoryChange{
    private int id;
    private Charity charity;
    private Food food;
    private Date date;
    private int qtyDonation;
    
    public Claim(){
        
    }
    
    public Claim(Charity charity, Food food,
            Date date, int qtyDonation){
        this.charity = charity;
        this.food = food;
        this.date = date;
        this.qtyDonation = qtyDonation;
    }
    
    public Claim(int id, Charity charity, Food food,
            Date date, int qtyDonation){
        this.id = id;
        this.charity = charity;
        this.food = food;
        this.date = date;
        this.qtyDonation = qtyDonation;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Charity getCharity() { return charity; }
    public void setCharity(Charity charity) { this.charity = charity; }

    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public int getQtyDonation() { return qtyDonation; }
    public void setQtyDonation(int qtyDonation) { this.qtyDonation = qtyDonation; }

   public ClaimTransaction createTransaction() {
        ClaimTransaction transaction = null;
        Order order = null;
        Claim claim = this;
        int qtyNormal = 0;
        int qtyDiscount = 0;
        int this_qtyDonation = this.getQtyDonation();
        
        ClaimTransactionCreator creator = new ClaimTransactionCreator();
        transaction = creator.createTransaction(
                this.getFood(),
                this.getCharity(),
                order,
                claim,
                qtyNormal, 
                qtyDiscount, 
                - this_qtyDonation
        );
        
        return transaction;
    } 
}
