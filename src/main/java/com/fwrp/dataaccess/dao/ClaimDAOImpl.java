package com.fwrp.dataaccess.dao;

import com.fwrp.models.Claim;
import com.fwrp.models.Food;
import com.fwrp.models.Charity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ClaimDAO interface for managing claims in the database.
 * 
 * This class provides methods for creating, retrieving, and deleting claims.
 * It uses SQL queries to interact with the database and manage claim records.
 * 
 * @author Ke Yan
 * @version 2.0
 */
public class ClaimDAOImpl implements ClaimDAO {

    /**
     * SQL query for inserting a new claim into the database.
     */
    private static final String INSERT_CLAIM_SQL = "INSERT INTO claims (user_id, food_id, date, quantity) VALUES (?, ?, ?, ?)";

    /**
     * SQL query for deleting a claim from the database by its ID.
     */
    private static final String DELETE_CLAIM_SQL = "delete from claims where id=?";

    /**
     * SQL query for selecting claims by user ID.
     */
    private static final String SELECT_CLAIMS_BY_USER_ID_SQL = "SELECT t1.id,"
            + "t1.user_id, t1.food_id, t1.date, t1.quantity, t2.discount,"
            + "t2.expire_days, t2.name as food_name, t3.firstname, t3.lastname, t3.phone, "
            + "t3.email, t3.type, t3.organization, t3.password, "
            + "t2.unitprice "
            + "FROM claims t1, foods t2, users t3 "
            + "WHERE user_id = ? and t1.food_id = t2.id and t1.user_id = t3.id";

    /**
     * DAO for managing inventory-related operations.
     */
    private InventoryDAO inventoryDAO = new InventoryDAOImpl();

    /**
     * DAO for managing expiration information.
     */
    private ExpireInfoDAO expireInfoDAO = new ExpireInfoDAOImpl();

    /**
     * Creates a new claim in the database.
     * 
     * @param claim the Claim object to be created
     * @param conn the Connection object to the database
     * @return the ID of the newly created claim
     * @throws SQLException if a database access error occurs
     */
    @Override
    public int createClaim(Claim claim, Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_CLAIM_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, claim.getCharity().getId());
            pstmt.setInt(2, claim.getFood().getId());
            pstmt.setDate(3, new java.sql.Date(claim.getDate().getTime()));
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

    /**
     * Deletes a claim by its ID.
     * 
     * @param id the ID of the claim to be deleted
     * @param conn the Connection object to the database
     * @return the number of rows affected by the delete operation
     * @throws SQLException if a database access error occurs
     */
    @Override
    public int deleteClaimById(int id, Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(DELETE_CLAIM_SQL)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves a list of claims by user ID.
     * 
     * @param userId the ID of the user whose claims are to be retrieved
     * @param conn the Connection object to the database
     * @return a list of Claim objects associated with the specified user ID
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Claim> getClaimByUserId(int userId, Connection conn) throws SQLException {
        List<Claim> claimList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(SELECT_CLAIMS_BY_USER_ID_SQL)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Food food = new Food();
                    food.setId(rs.getInt("food_id"));
                    food.setName(rs.getString("food_name"));
                    food.setDiscount(rs.getDouble("discount"));
                    food.setUnitPrice(rs.getDouble("unitprice"));
                    food.setExpireDays(rs.getInt("expire_days"));

                    Charity charity = new Charity();
                    charity.setFirstName(rs.getString("firstname"));
                    charity.setLastName(rs.getString("lastname"));
                    charity.setPhone(rs.getString("phone"));
                    charity.setEmail(rs.getString("email"));
                    charity.setPassword(rs.getString("password"));
                    charity.setType(rs.getInt("type"));
                    charity.setOrganization(rs.getString("organization"));

                    Claim claim = new Claim(
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
