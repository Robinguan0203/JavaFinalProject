package com.fwrp.dataaccess.dao;

import com.fwrp.models.Claim;
import com.fwrp.models.Food;
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
    private static final String DELETE_CLAIM_SQL = "delete from claims where id=?";
    private static final String SELECT_CLAIMS_BY_USER_ID_SQL = "SELECT t1.id,t1.user_id,t1.food_id,t1.date,t1.quantity,t2.discount,t2.expire_days,t2.`name` as food_name,t2.unitprice FROM claims t1,foods t2 WHERE user_id = ? \n" +
            "and t1.food_id=t2.id";

    private InventoryDAO inventoryDAO = new InventoryDAOImpl();
    private ExpireInfoDAO expireInfoDAO = new ExpireInfoDAOImpl();

    @Override
    public boolean createClaim(Claim claim, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(INSERT_CLAIM_SQL)) {
            
            pstmt.setInt(1, claim.getUserId());
            pstmt.setInt(2, claim.getFood().getId());
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

    public int deleteClaimById(int id,Connection conn) throws SQLException{

        try(PreparedStatement pstmt = conn.prepareStatement(DELETE_CLAIM_SQL)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate();
        }

    }
    @Override
    public List<Claim> getClaimByUserId(int userId, Connection conn)  throws SQLException{
        List<Claim> claimList= new ArrayList<>();
        Claim claim = null;
        Food food=null;
        try(PreparedStatement pstmt = conn.prepareStatement(SELECT_CLAIMS_BY_USER_ID_SQL)){            
            pstmt.setInt(1, userId);
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    food=new Food();
                    food.setId(rs.getInt("food_id"));
                    food.setName(rs.getString("food_name"));
                    food.setDiscount(rs.getDouble("discount"));
                    food.setUnitPrice(rs.getDouble("unitprice"));
                    food.setExpireDays(rs.getInt("expire_days"));
                    claim = new Claim(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                            food,
                        rs.getDate("date"),
                        rs.getInt("quantity")
                    );
                    claimList.add(claim);
                }
            }            
        } 
        
        return claimList;
    }
}
