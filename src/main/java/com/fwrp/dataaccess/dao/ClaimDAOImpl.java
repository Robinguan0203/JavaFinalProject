package com.fwrp.dataaccess.dao;

import com.fwrp.models.Claim;
import com.fwrp.models.Inventory;
import com.fwrp.models.ExpireInfo;
import com.fwrp.dataaccess.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAOImpl implements ClaimDAO {
    private static final String INSERT_CLAIM_SQL = "INSERT INTO claims (user_id, food_id, date, quantity) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CLAIMS_BY_USER_ID_SQL = "SELECT * FROM claims WHERE user_id = ?";

    private InventoryDAO inventoryDAO = new InventoryDAOImpl();
    private ExpireInfoDAO expireInfoDAO = new ExpireInfoDAOImpl();

    @Override
    public boolean createClaim(Claim claim, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(INSERT_CLAIM_SQL)) {
            
            pstmt.setInt(1, claim.getUserId());
            pstmt.setInt(2, claim.getFoodId());
            pstmt.setDate(3, claim.getDate());
            pstmt.setInt(4, claim.getQuantity());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }       
            /*// Update inventory
            InventoryDTO inventory = inventory.getInventoryByFoodId(claim.getFoodId(), Connection conn);
            if (inventory != null) {
                int newQuantity = inventory.getQuantity() - claim.getQuantity();
                inventory.updateInventory(claim.getFoodId(), newQuantity);
            }

            // Update expire info
            ExpireInfo expireInfo = expireInfoDAO.getExpireInfoByFoodId(claim.getFoodId());
            if (expireInfo != null) {
                // Assuming expiration date needs to be recalculated based on new quantity
                expireInfoDAO.updateExpireInfo(claim.getFoodId(), expireInfo.getExpireDate());
            }*/
        } 
        
        return isSuccess;
        
    }

    
    @Override
    public Claim getClaimByUserId(int userId, Connection conn)  throws SQLException{       
        Claim claim = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CLAIMS_BY_USER_ID_SQL)){            
            pstmt.setInt(1, userId);
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    claim = new Claim(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("food_id"),
                        rs.getDate("date"),
                        rs.getInt("quantity")
                    );
                }
            }            
        } 
        
        return claim;
    }
}
