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
 *
 * @author Ke Yan
 */
public class ClaimDbService {
    private ClaimDAO claimDAO = null;
    private InventoryDAO inventoryDAO = null;
    public ClaimDbService(){
        claimDAO = new ClaimDAOImpl(); 
        inventoryDAO = new InventoryDAOImpl(); 
    }
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
