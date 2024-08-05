package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.InventoryDbService;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents a Retailer user, extending the User class.
 * This class provides functionality to create inventory changes and retrieve surplus items.
 */
public class Retailer extends User {

    /**
     * Creates an inventory change (manage inventory change) for the retailer user.
     * 
     * @param food The food item for which the inventory change is being created.
     * @param qtyNormal The normal quantity of the food item.
     * @param qtyDiscount The discounted quantity of the food item.
     * @param qtyDonation The donated quantity of the food item.
     * @return ManageInventoryChange The created manage inventory change representing the inventory change.
     */
    public ManageInventoryChange createInventorychange(Food food, 
            int qtyNormal, int qtyDiscount, int qtyDonation){
        
        ManageInventoryChangeCreator creator = new ManageInventoryChangeCreator();
        ManageInventoryChange manageInventoryChange = creator.createInventoryChange(food, this, qtyNormal, qtyDiscount, qtyDonation);
        return manageInventoryChange;
    }
    
	/**
     * Retrieves surplus items that are close to expiration.
     * 
     * @return {@code ArrayList<ExpireInfo>} A list of surplus items with expiration information.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
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
