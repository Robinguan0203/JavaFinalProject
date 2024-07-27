/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class implements the ExpireInfoDAO interface to provide a concrete implementation.
 * It provides methods to add, retrieve, and remove users from a data source.
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
     * @return Boolean Returns true if the course was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
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
    
}
