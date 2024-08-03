package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.InventoryDbService;
import java.sql.SQLException;
import java.util.ArrayList;

public class Charity extends User {

    @Override
    public void login(){
        
    }
    
    @Override
    public void logout(){
        
    }

     public Claim createInventorychange(Food food, 
            int qtyNormal, int qtyDiscount, int qtyDonation){
        
        ClaimCreator creator = new ClaimCreator();
        Claim claim = creator.createInventoryChange(food, this, qtyNormal, qtyDiscount, qtyDonation);
        return claim;
    }    
}
