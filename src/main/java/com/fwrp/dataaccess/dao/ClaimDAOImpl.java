package com.fwrp.dataaccess.dao;

import com.fwrp.models.Claim;
import com.fwrp.models.Food;
import com.fwrp.models.Inventory;
import com.fwrp.models.ExpireInfo;
import com.fwrp.dataaccess.DataSource;
import com.fwrp.models.Charity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClaimDAOImpl implements ClaimDAO {
    private static final String INSERT_CLAIM_SQL = "INSERT INTO claims (user_id, food_id, date, quantity) VALUES (?, ?, ?, ?)";
    private static final String DELETE_CLAIM_SQL = "delete from claims where id=?";
    private static final String SELECT_CLAIMS_BY_USER_ID_SQL = "SELECT t1.id,"
            + "t1.user_id,t1.food_id,t1.date,t1.quantity,t2.discount,"
            + "t2.expire_days,t2.name as food_name,t3.firstname, t3.lastname, t3.phone, "
            + "t3.email, t3.type, t3.organization, t3.password,"
            + "t2.unitprice "
            + "FROM claims t1,foods t2, users t3 "
            + "WHERE user_id = ? and t1.food_id = t2.id and t1.user_id = t3.id";

    private InventoryDAO inventoryDAO = new InventoryDAOImpl();
    private ExpireInfoDAO expireInfoDAO = new ExpireInfoDAOImpl();

    @Override
    public int createClaim(Claim claim, Connection conn) throws SQLException {
        try(PreparedStatement pstmt = conn.prepareStatement(INSERT_CLAIM_SQL,Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, claim.getCharity().getId());
            pstmt.setInt(2, claim.getFood().getId());
            pstmt.setDate(3,new java.sql.Date(claim.getDate().getTime()));
            pstmt.setInt(4, claim.getQtyDonation());
            
            int affectedRows = pstmt.executeUpdate();      
            if (affectedRows == 0) {
                throw new SQLException("Creating claim failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating claim failed, no ID obtained.");
                }
            }
        } 
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
        Charity charity = null;
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
                    charity = new Charity();
                    charity.setFirstName(rs.getString("firstname"));
                    charity.setLastName(rs.getString("lastname"));
                    charity.setPhone(rs.getString("phone"));
                    charity.setEmail(rs.getString("email"));
                    charity.setPassword(rs.getString("password"));
                    charity.setType(rs.getInt("type"));
                    charity.setOrganization(rs.getString("organization"));
                    claim = new Claim(
                        rs.getInt("id"),
                        charity,
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
