package com.fwrp.dataaccess.dao;

import com.fwrp.models.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDAO {
    int createOrder(Order order, Connection conn) throws SQLException;
    List<Order> getOrderByUserId(int userId, Connection conn) throws SQLException;
    int deleteOrderById(int id,Connection conn) throws SQLException;
}
