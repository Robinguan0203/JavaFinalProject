/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;


import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.ClaimDAO;
import com.fwrp.dataaccess.dao.ClaimDAOImpl;
import com.fwrp.dataaccess.dao.InventoryDAO;
import com.fwrp.dataaccess.dao.InventoryDAOImpl;
import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Claim;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides database services for managing claims.
 * It interacts with the ClaimDAO and InventoryDAO to perform operations such as creating, deleting, and retrieving claims.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public class ClaimDbService {
	/**
	 *  Data Access Object for claims
	 */
    private ClaimDAO claimDAO = null;
	
	/**
	 * Data Access Object for inventory
	 */
    private InventoryDAO inventoryDAO = null;
	
	/**
     * Constructor for ClaimDbService.
     * Initializes the ClaimDAO and InventoryDAO implementations.
     */
    public ClaimDbService(){
        claimDAO = new ClaimDAOImpl(); 
        inventoryDAO = new InventoryDAOImpl(); 
    }
	
	/**
     * Creates a new claim in the database.
     * 
     * @param claim The claim object to be created.
     * @return int The ID of the newly created claim.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws DataInsertionFailedException if the data insertion fails.
     */
    public int CreateClaim(Claim claim) throws SQLException, ClassNotFoundException, DataInsertionFailedException {
        Connection conn = null;
        int claimId;
        
        try{
            conn = DataSource.getInstance().getConnection();            
            claimId = claimDAO.createClaim(claim, conn); 
        } catch(SQLException e){
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close(); 
                } catch (SQLException e) {
                    e.printStackTrace(); 
                }
            }
        }
        
        System.out.println("New claim created");
        return claimId;
    }

	/**
     * Deletes a claim from the database by its ID.
     * 
     * @param id The ID of the claim to be deleted.
     * @return int The number of rows affected by the delete operation.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public int deleteClaimById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return claimDAO.deleteClaimById(id, conn);
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
    }
	
	/**
     * Retrieves a list of claims by user ID.
     * 
     * @param userId The ID of the user whose claims are to be retrieved.
     * @return List<Claim> A list of claims associated with the specified user ID.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    public List<Claim> getClaimByUserId(int userId) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return claimDAO.getClaimByUserId(userId, conn);
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
    }
}
