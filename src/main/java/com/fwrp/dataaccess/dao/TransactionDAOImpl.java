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
 * It provides methods to add, retrieve, and remove transactions from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class TransactionDAOImpl implements TransactionDAO {

    /**
     * Adds a new transaction to the data source.
     * 
     * @param transactionDTO The transaction object to be added.
     * @param conn SQL connection
     * @return boolean Returns true if the transaction was successfully added, false otherwise.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
    @Override
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

    /**
     * Deletes a transaction from the data source.
     * 
     * @param transactionDTO The transaction object to be deleted.
     * @param conn SQL connection
     * @return boolean Returns true if the transaction was successfully deleted, false otherwise.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
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
    
    /**
     * Retrieves all transactions from the data source.
     * 
     * @param conn SQL connection
     * @return ArrayList<TransactionDTO> A list of all transactions.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
    @Override
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
    
    @Override
    public ArrayList<TransactionDTO> getTransactionsByUserId(int userId, Connection conn) throws SQLException{
        ArrayList<TransactionDTO> transactionDTOs = new ArrayList<>();
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM "
                + "transactions WHERE user_id = ? ORDER BY id DESC")){
            pstmt.setInt(1, userId);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    int id = rs.getInt("id");
                    int foodId = rs.getInt("food_id");
                    Integer orderId = rs.getObject("order_id") != null ? rs.getInt("order_id") : 0;
                    Integer claimId = rs.getObject("claim_id") != null ? rs.getInt("claim_id") : 0;
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
