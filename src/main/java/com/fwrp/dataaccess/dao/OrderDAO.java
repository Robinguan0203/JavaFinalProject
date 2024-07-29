package com.fwrp.dataaccess.dao;

import com.fwrp.models.Order;
import com.fwrp.models.PurchaseTransaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author YAOZHOU XIE
 */

public interface OrderDAO {
    boolean createOrder(Order order, Connection conn) throws SQLException;
    Order getOrderById(int id, Connection conn) throws SQLException;
    ArrayList<Order> getOrdersByConsumerId(int consumerId, Connection conn) throws SQLException;
    boolean updateOrder(Order order, Connection conn) throws SQLException;
    boolean deleteOrder(int id, Connection conn) throws SQLException;
    
    void storeOrder();
    PurchaseTransaction createTransaction();
}
