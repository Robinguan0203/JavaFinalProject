package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.InventoryDbService;
import java.sql.SQLException;
import java.util.ArrayList;

public class Consumer extends User {

    @Override
    public void login(){
        
    }
    
    @Override
    public void logout(){
        
    }

     public Order createInventorychange(Food food, 
            int qtyNormal, int qtyDiscount, int qtyDonation){
        
        OrderCreator creator = new OrderCreator();
        Order order = creator.createInventoryChange(food, this, qtyNormal, qtyDiscount, qtyDonation);
        return order;
    }    
}
