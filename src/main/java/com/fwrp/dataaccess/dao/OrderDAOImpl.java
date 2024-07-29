/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.models.Order;
import com.fwrp.models.PurchaseTransaction;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author YAOZHOU XIE
 */
public class OrderDAOImpl implements OrderDAO {
    boolean isSuccess = false;
    @Override
    public boolean createOrder(Order order, Connection conn) throws SQLException{
        try (PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO orders (id, user_id, food_id, date, unitprice, discount, quantity)"
                            + "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, order.getId());
            pstmt.setInt(2, order.getConsumerId());
            pstmt.setInt(3, order.getFoodId());
            pstmt.setDate(4, (Date) order.getDate());
            pstmt.setDouble(5, order.getUnitPrice());
            pstmt.setDouble(6, order.getDiscount());
            pstmt.setInt(7, order.getQuantity());
            
            if(pstmt.executeUpdate() == 1) {
                isSuccess = true;
                
        }
    }
    return isSuccess;
}
        
//            boolean isSuccess = false;
//
//        try(PreparedStatement pstmt = conn.prepareStatement(
//                    "INSERT INTO foods (name, expire_days, unitprice, discount)"
//                            + "VALUES(?, ?, ?, ?)")) {
//            
//            pstmt.setString(1, food.getName());
//            pstmt.setInt(2, food.getExpireDays());
//            pstmt.setDouble(3, food.getUnitPrice());
//            pstmt.setDouble(4, food.getDiscount());
//            
//            if(pstmt.executeUpdate() == 1){
//                isSuccess = true;
//            }            
//        } 
//        
//        return isSuccess;

    public Order getOrderById(int id, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Order> getOrdersByConsumerId(int consumerId, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateOrder(Order order, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean deleteOrder(int id, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void storeOrder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PurchaseTransaction createTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
