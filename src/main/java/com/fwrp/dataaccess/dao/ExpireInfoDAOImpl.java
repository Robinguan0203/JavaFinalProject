/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * This class provides a concrete implementation of the ExpireInfoDAO interface.
 * It performs CRUD operations on the ExpireInfo model object.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class ExpireInfoDAOImpl implements ExpireInfoDAO{

    /**
     * Adds a new expireInfo to the data source.
     * 
     * @param expireInfoDTO The ExpireInfoDTO object to be added.
     * @param conn SQL connection
     * @return Boolean Returns true if the expireInfo was successfully added, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean addExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn) throws SQLException {
        boolean isSuccess = false;
        
        try(PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO expire_infos (food_id, quantity, expire_date,"
                        + " is_surplus) VALUES (?,?,?,?)")){
            
            pstmt.setInt(1, expireInfoDTO.getFoodId());
            pstmt.setInt(2, expireInfoDTO.getQuantity());
            pstmt.setDate(3,new java.sql.Date(expireInfoDTO.getExpireDate().getTime()));
            pstmt.setBoolean(4, expireInfoDTO.isIsSurplus());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }
    
    /**
     * Retrieves an expireInfo by its ID.
     * 
     * @param expireInfoId The ID of the expireInfo to retrieve.
     * @param conn SQL connection
     * @return ExpireInfoDTO Returns the ExpireInfoDTO object if found, null otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public ExpireInfoDTO getExpireInfoById(int expireInfoId, Connection conn) throws SQLException{
        ExpireInfoDTO expireInfoDTO = new ExpireInfoDTO();
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM expire_infos WHERE id = ?")){
            pstmt.setInt(1, expireInfoId);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
  
                    expireInfoDTO.setId(rs.getInt("id"));
                    expireInfoDTO.setFoodId(rs.getInt("food_id"));
                    expireInfoDTO.setExpireDate(rs.getDate("expire_date"));
                    expireInfoDTO.setQuantity(rs.getInt("quantity"));
                    expireInfoDTO.setIsSurplus(rs.getBoolean("is_surplus"));
                }
            }
        }
        return expireInfoDTO;
    }

    /**
     * Retrieves expireInfos by food ID, ordered by expire date.
     * 
     * @param foodID The food_id of the expireInfos to retrieve.
     * @param conn SQL connection
     * @return ArrayList<ExpireInfoDTO> A list of ExpireInfoDTO objects.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public ArrayList<ExpireInfoDTO> getExpireInfoByFoodId(int foodID, Connection conn) throws SQLException {
        ArrayList<ExpireInfoDTO> expireInfoDTOs = new ArrayList<>();
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM expire_infos WHERE food_id = ? ORDER BY expire_date ASC")){
            pstmt.setInt(1, foodID);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    ExpireInfoDTO expireInfoDTO = new ExpireInfoDTO();
                    expireInfoDTO.setId(rs.getInt("id"));
                    expireInfoDTO.setFoodId(rs.getInt("food_id"));
                    expireInfoDTO.setExpireDate(rs.getDate("expire_date"));
                    expireInfoDTO.setQuantity(rs.getInt("quantity"));
                    expireInfoDTO.setIsSurplus(rs.getBoolean("is_surplus"));
                    
                    expireInfoDTOs.add(expireInfoDTO);
                }
            }
        }
        
        return expireInfoDTOs;
    }

    /**
     * Retrieves expireInfos that are earlier than the designated expire date.
     * 
     * @param expireDate The expire date to compare.
     * @param conn SQL connection
     * @return ArrayList<ExpireInfoDTO> A list of ExpireInfoDTO objects.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public ArrayList<ExpireInfoDTO> getExpireInfoByExpireDate(Date expireDate, Connection conn) throws SQLException {
        ArrayList<ExpireInfoDTO> expireInfoDTOs = new ArrayList<>();
        System.out.println(expireDate);
        System.out.println(expireDate.getTime());
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM expire_infos WHERE expire_date < ? ORDER BY expire_date ASC")){
            pstmt.setDate(1, new java.sql.Date(expireDate.getTime()));
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    ExpireInfoDTO expireInfoDTO = new ExpireInfoDTO();
                    expireInfoDTO.setId(rs.getInt("id"));
                    expireInfoDTO.setFoodId(rs.getInt("food_id"));
                    expireInfoDTO.setExpireDate(rs.getDate("expire_date"));
                    expireInfoDTO.setQuantity(rs.getInt("quantity"));
                    expireInfoDTO.setIsSurplus(rs.getBoolean("is_surplus"));
                    
                    expireInfoDTOs.add(expireInfoDTO);
                }
            }
        }
        
        return expireInfoDTOs;
    }
    
    /**
     * Retrieves all expireInfos.
     * 
     * @param conn SQL connection
     * @return ArrayList<ExpireInfoDTO> A list of all ExpireInfoDTO objects.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public ArrayList<ExpireInfoDTO> getAllExpireInfo(Connection conn) throws SQLException {
        ArrayList<ExpireInfoDTO> expireInfoDTOs = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM expire_infos ORDER BY expire_date ASC")){
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    ExpireInfoDTO expireInfoDTO = new ExpireInfoDTO();
                    expireInfoDTO.setId(rs.getInt("id"));
                    expireInfoDTO.setFoodId(rs.getInt("food_id"));
                    expireInfoDTO.setExpireDate(rs.getDate("expire_date"));
                    expireInfoDTO.setQuantity(rs.getInt("quantity"));
                    expireInfoDTO.setIsSurplus(rs.getBoolean("is_surplus"));
                    
                    expireInfoDTOs.add(expireInfoDTO);
                }
            }
        }
        
        return expireInfoDTOs;
    }

    /**
     * Updates an existing expireInfo.
     * 
     * @param expireInfoDTO The updated ExpireInfoDTO object.
     * @param conn SQL connection
     * @return Boolean Returns true if the expireInfo was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean updateExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE expire_infos SET id = ?, food_id = ?, quantity = ?, "
                            + "expire_date = ?, is_surplus = ? WHERE id = ?")) {
            
            pstmt.setInt(1, expireInfoDTO.getId());
            pstmt.setInt(2, expireInfoDTO.getFoodId());
            pstmt.setInt(3, expireInfoDTO.getQuantity());
            pstmt.setDate(4,new java.sql.Date(expireInfoDTO.getExpireDate().getTime()));
            pstmt.setBoolean(5, expireInfoDTO.isIsSurplus());
            pstmt.setInt(6, expireInfoDTO.getId());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        } 
        
        return isSuccess;
    }

    /**
     * Deletes an expireInfo from the data source.
     * 
     * @param expireInfoDTO The ExpireInfoDTO object to be removed.
     * @param conn SQL connection
     * @return Boolean Returns true if the expireInfo was successfully deleted, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean deleteExpireInfo(ExpireInfoDTO expireInfoDTO, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM expire_infos WHERE id = ?")) {
            
            pstmt.setInt(1, expireInfoDTO.getId());
                        
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }
    
    /**
     * Retrieves a summary of food surplus.
     * 
     * @param conn SQL connection
     * @return HashMap<Integer, Integer[]> A map where the key is the food ID and the value is an array containing surplus quantities and inventory quantities.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public HashMap<Integer, Integer[]> getFoodSurplusSummary(Connection conn) throws SQLException{
        HashMap<Integer, Integer[]> foodSurplusMap = new HashMap<>();
        
        try(PreparedStatement pstmt = conn.prepareStatement(
                "SELECT "
                        + "expire_infos.food_id, "
                        + "SUM(quantity) AS surplus_qty,"
                        + "inventory.quantity_normal AS qty_normal,"
                        + "inventory.quantity_discount AS qty_discount, "
                        + "inventory.quantity_donation AS qty_donation "
                        + "FROM expire_infos "
                        + "LEFT JOIN inventory "
                        + "ON inventory.food_id = expire_infos.food_id "
                        + "WHERE is_surplus = true "
                        + "GROUP BY "
                        + "expire_infos.food_id, "
                        + "inventory.quantity_normal, "
                        + "inventory.quantity_discount, "
                        + "inventory.quantity_donation;")){
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    int foodId = rs.getInt("food_id");
                    int surplusQty = rs.getInt("surplus_qty");
                    int qtyNormal = rs.getInt("qty_normal");
                    int qtyDiscount = rs.getInt("qty_discount");
                    int qtyDonation = rs.getInt("qty_donation");
                    
                    foodSurplusMap.put(foodId, new Integer[]{surplusQty, qtyNormal, qtyDiscount, qtyDonation});
                }
            }
        }
        
        return foodSurplusMap;
    }
}
