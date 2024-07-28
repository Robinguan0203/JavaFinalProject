/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.TransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class implements the TransactionDAO interface to provide a concrete implementation.
 * It provides methods to add, retrieve, and remove users from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class TransactionDAOImpl implements TransactionDAO {

    public boolean addTransaction(TransactionDTO transactionDTO, Connection conn) throws SQLException {        
        boolean isSuccess = false;
        
        try(PreparedStatement pstmt = conn.prepareStatement("INSERT INTO transactions ("
                + "food_id, user_id, order_id,claim_id, date, type, quantity_normal, "
                + "quantity_discount, quantity_donation) VALUES ("
                + "?,?,?,?,?,?,?,?,?)")){
                       
            pstmt.setInt(1,transactionDTO.getFoodId());
            pstmt.setInt(2,transactionDTO.getUserId());
            if(transactionDTO.getOrderId() == null){
                pstmt.setNull(3, Types.INTEGER);
            } else{
                pstmt.setInt(3,transactionDTO.getOrderId());
            }
            
            if(transactionDTO.getClaimId() == null){
                pstmt.setNull(4, Types.INTEGER);
            } else{
                pstmt.setInt(4,transactionDTO.getClaimId());
            }
            
            pstmt.setDate(5,new java.sql.Date(transactionDTO.getDate().getTime()));
            pstmt.setInt(6,transactionDTO.getType());
            pstmt.setInt(7,transactionDTO.getQtyNormal());
            pstmt.setInt(8,transactionDTO.getQtyDiscount());
            pstmt.setInt(9,transactionDTO.getQtyDonation());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        } 
        
        return isSuccess;
    }

    @Override
    public boolean deleteTransaction(TransactionDTO transactionDTO, Connection conn) throws SQLException {
        boolean isSuccess = false;
        
        try(PreparedStatement pstmt = conn.prepareStatement("DELETE FROM transactions WHERE id = ?")){
            
            pstmt.setInt(1,transactionDTO.getId());           
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        } 
        
        return isSuccess;
    }
    
    public ArrayList<TransactionDTO> getAllTransactions(Connection conn) throws SQLException{
        ArrayList<TransactionDTO> transactionDTOs = new ArrayList<>();
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactions ORDER BY id DESC")){
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    int id = rs.getInt("id");
                    int foodId = rs.getInt("food_id");
                    int userId = rs.getInt("user_id");
                    int orderId = rs.getInt("order_id");
                    int claimId = rs.getInt("claim_id");
                    Date operateDate = rs.getDate("date");
                    int type = rs.getInt("type");
                    int qtyNormal = rs.getInt("quantity_normal");
                    int qtyDiscount = rs.getInt("quantity_discount");
                    int qtyDonation = rs.getInt("quantity_Donation");
                    
                    TransactionDTO dto = new TransactionDTO();
                    dto.setId(id);
                    dto.setFoodId(foodId);
                    dto.setUserId(userId);
                    dto.setOrderId(orderId);
                    dto.setClaimId(claimId);
                    dto.setDate(operateDate);
                    dto.setType(type);
                    dto.setQtyNormal(qtyNormal);
                    dto.setQtyDiscount(qtyDiscount);
                    dto.setQtyDonation(qtyDonation);
                    
                    transactionDTOs.add(dto);
                }
            }
        }
        
        return transactionDTOs;
    }
    
}
