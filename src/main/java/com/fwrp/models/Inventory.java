package com.fwrp.models;

public class Inventory {
    private int id;
    private Food food;
    private int qtyNormal;
    private int qtyDiscount;
    private int qtyDonation;

    public Inventory() {
    }

    public Inventory(int id, Food food, int qtyNormal, int qtyDiscount, int qtyDonation) {
        this.id = id;
        this.food = food;
        this.qtyNormal = qtyNormal;
        this.qtyDiscount = qtyDiscount;
        this.qtyDonation = qtyDonation;
    }

    public int getId() {
        return id;
    }

    public Food getFood() {
        return food;
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

    public void setFood(Food food) {
        this.food = food;
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
