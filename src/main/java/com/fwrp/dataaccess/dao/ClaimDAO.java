package com.fwrp.dataaccess.dao;

import com.fwrp.models.Claim;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) interface for managing claims.
 * 
 * This interface defines methods for creating, retrieving, and deleting claims in the database.
 * 
 * @author Ke Yan
 * @version 2.0
 */
public interface ClaimDAO {

    /**
     * Creates a new claim in the database.
     * 
     * @param claim the Claim object to be created
     * @param conn the Connection object to the database
     * @return the ID of the newly created claim
     * @throws SQLException if a database access error occurs
     */
    int createClaim(Claim claim, Connection conn) throws SQLException;

    /**
     * Retrieves a list of claims by user ID.
     * 
     * @param userId the ID of the user whose claims are to be retrieved
     * @param conn the Connection object to the database
     * @return a list of Claim objects associated with the specified user ID
     * @throws SQLException if a database access error occurs
     */
    List<Claim> getClaimByUserId(int userId, Connection conn) throws SQLException;

    /**
     * Deletes a claim by its ID.
     * 
     * @param id the ID of the claim to be deleted
     * @param conn the Connection object to the database
     * @return the number of rows affected by the delete operation
     * @throws SQLException if a database access error occurs
     */
    int deleteClaimById(int id, Connection conn) throws SQLException;
}
