package com.fwrp.models;

public class Charity extends User {

    @Override
    public void login(){
        
    }
    
    @Override
    public void logout(){
        
    }

    public ManageInventoryChange createInventorychange(Food food,
                                                       int qtyNormal, int qtyDiscount, int qtyDonation){

        ManageInventoryChangeCreator creator = new ManageInventoryChangeCreator();
        ManageInventoryChange manageInventoryChange = creator.createInventoryChange(food, this, qtyNormal, qtyDiscount, qtyDonation);
        return manageInventoryChange;
    }
}
