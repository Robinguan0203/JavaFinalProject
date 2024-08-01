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
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Claim;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public boolean CreateClaim(Claim claim) throws DataAlreadyExistsException, DataInsertionFailedException, Exception {      
        Connection conn = null;
        
        try{
            conn = DataSource.getInstance().getConnection();
            //start transaction
            conn.setAutoCommit(false);                      
            
            boolean isClaimAdded = claimDAO.createClaim(claim, conn); 
            if(!isClaimAdded){
                throw new DataInsertionFailedException("Failed to add claim.");
            } 
            
            
            
            conn.commit(); // 提交事务
        } catch(SQLException e){
            if (conn != null) {
                conn.rollback(); // 回滚事务
            }   
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); 
                    conn.close(); 
                } catch (SQLException e) {
                    e.printStackTrace(); 
                }
            }
        }
        
        System.out.println("New claim created");
        return true;
    }
}
