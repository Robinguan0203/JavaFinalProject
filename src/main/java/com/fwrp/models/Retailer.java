package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.InventoryDbService;
import java.sql.SQLException;
import java.util.ArrayList;


public class Retailer extends User {

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
    
    public ArrayList<ExpireInfo> getSurplusItems() throws SQLException, ClassNotFoundException {
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();        
        InventoryDbService dbService = new InventoryDbService();
        
        ArrayList<ExpireInfoDTO> expireDTOs = dbService.queryExpireInfoClosedToExpire();
        
        if(!expireDTOs.isEmpty()){
            for(ExpireInfoDTO dto : expireDTOs){
                expireInfos.add(dto.transferToExpireInfo());
            }
        } 
        
        return expireInfos;
    }


    
}
