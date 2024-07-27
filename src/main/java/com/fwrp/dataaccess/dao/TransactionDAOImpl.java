/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.TransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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
    
}
