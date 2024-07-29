/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.OrderDTO;
import com.fwrp.models.PurchaseTransaction;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author YAOZHOU XIE
 */
public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean addOrder(OrderDTO orderDTO, Connection conn) throws SQLException{

        boolean isSuccess = false;

        try (PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO orders (user_id, food_id, date, unitprice, discount, quantity)"
                            + "VALUES(?, ?, ?, ?, ?, ?)")) {
            
            pstmt.setInt(1, orderDTO.getConsumerId());
            pstmt.setInt(2, orderDTO.getFoodId());
            pstmt.setDate(3, (Date) orderDTO.getDate());
            pstmt.setDouble(4, orderDTO.getUnitPrice());
            pstmt.setDouble(5, orderDTO.getDiscount());
            pstmt.setInt(6, orderDTO.getQuantity());
            
            if(pstmt.executeUpdate() == 1) {
                isSuccess = true;
                
        }
    }
    return isSuccess;
}
        
    public OrderDTO getOrderById(int id, Connection conn) throws SQLException{
        OrderDTO orderDTO = null;
        
         try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orders WHERE id = ?")){            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    orderDTO = new OrderDTO(
                            rs.getInt("id"),
                            rs.getInt("quantity"),
                            rs.getDate("date"),
                            rs.getDouble("unitprice"),
                            rs.getDouble("discount"),
                            rs.getInt("food_id"),
                            rs.getInt("consumer_id")
                    );
                }
            }  
        } 
        
        return orderDTO;
    }
    

    public ArrayList<OrderDTO> getAllOrders(Connection conn) throws SQLException {
        ArrayList<OrderDTO> orders = new ArrayList<>();
        
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orders")) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderDTO orderDTO = new OrderDTO(
                            rs.getInt("id"),
                            rs.getInt("quantity"),
                            rs.getDate("date"),
                            rs.getDouble("unitprice"),
                            rs.getDouble("discount"),
                            rs.getInt("food_id"),
                            rs.getInt("consumer_id")
                    );
                    orders.add(orderDTO);
                }
            }
        }
        return orders;
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE orders SET quantity = ?, date = ?, unitprice = ?, discount = ?, food_id = ?, consumer_id = ? WHERE id = ?")) {

            pstmt.setInt(1, orderDTO.getQuantity());
            pstmt.setDate(2, new java.sql.Date(orderDTO.getDate().getTime()));
            pstmt.setDouble(3, orderDTO.getUnitPrice());
            pstmt.setDouble(4, orderDTO.getDiscount());
            pstmt.setInt(5, orderDTO.getFoodId());
            pstmt.setInt(6, orderDTO.getConsumerId());
            pstmt.setInt(7, orderDTO.getId());

            if (pstmt.executeUpdate() == 1) {
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    @Override
    public boolean removeOrder(int id, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM orders WHERE id = ?")) {

            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() == 1) {
                isSuccess = true;
            }
        }

        return isSuccess;
    }
    
    //TODO
    public void storeOrder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //TODO
    public PurchaseTransaction createTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
