/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.InventoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements the InventoryDAO interface to provide a concrete implementation.
 * It provides methods to add, retrieve, and remove users from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class InventoryDAOImpl implements InventoryDAO {
    
    public boolean addInventoryByFoodId(int foodId, Connection conn)  throws SQLException{
        boolean isSuccess = false;
        
        try(PreparedStatement pstmt = conn.prepareStatement("INSERT INTO inventory (food_id, "
                    + "quantity_normal, quantity_discount, quantity_donation) "
                    + "VALUES (?,0,0,0)")) {            
            
            pstmt.setInt(1, foodId);            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        } 
        
        return isSuccess;
    }

    public InventoryDTO getInventoryByFoodId(int foodId, Connection conn)  throws SQLException{
        InventoryDTO inventoryDTO  = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM inventory WHERE food_id = ?"
                    )) {            
            
            pstmt.setInt(1, foodId);   
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    inventoryDTO = new InventoryDTO(
                        rs.getInt("id"),
                        rs.getInt("food_id"),
                        rs.getInt("quantity_normal"),
                        rs.getInt("quantity_discount"),
                        rs.getInt("quantity_donation")
                    );                
                }
            }            
        }         
        
        return inventoryDTO;
    }

    public boolean updateInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException{        
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE inventory SET id = ?, food_id = ?, "
                            + "quantity_normal = ?, quantity_discount = ?, "
                            + "quantity_donation = ? "
                            + "WHERE id = ?")) {
                       
            pstmt.setInt(1, inventoryDTO.getId());
            pstmt.setInt(2, inventoryDTO.getFoodId());
            pstmt.setInt(3, inventoryDTO.getQtyNormal());
            pstmt.setInt(4, inventoryDTO.getQtyDiscount());
            pstmt.setInt(5, inventoryDTO.getQtydonation());            
            pstmt.setInt(6, inventoryDTO.getId());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        } 
        
        return isSuccess;
    }

    public boolean removeInventory(InventoryDTO inventoryDTO, Connection conn)  throws SQLException{        
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM inventory WHERE id = ?")) {
            
            pstmt.setInt(1, inventoryDTO.getId());
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        } 
        
        return isSuccess;
    }
    
}
