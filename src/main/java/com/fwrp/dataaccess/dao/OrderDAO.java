package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.OrderDTO;
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
    boolean addOrder(OrderDTO orderDTO, Connection conn) throws SQLException;
    OrderDTO getOrderById(int id, Connection conn) throws SQLException;
    ArrayList<OrderDTO> getAllOrders(Connection conn) throws SQLException;
    boolean updateOrder(OrderDTO orderDTO, Connection conn) throws SQLException;
    boolean removeOrder(int id, Connection conn) throws SQLException;
    
    void storeOrder();
    PurchaseTransaction createTransaction();
}
