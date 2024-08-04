package com.fwrp.dbService;


import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.OrderDAO;
import com.fwrp.dataaccess.dao.OrderDAOImpl;
import com.fwrp.dataaccess.dao.InventoryDAO;
import com.fwrp.dataaccess.dao.InventoryDAOImpl;
import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ke Yan
 */
public class OrderDbService {
    private OrderDAO orderDAO = null;
    private InventoryDAO inventoryDAO = null;
    public OrderDbService(){
        orderDAO = new OrderDAOImpl(); 
        inventoryDAO = new InventoryDAOImpl(); 
    }
    public int CreateOrder(Order order) throws SQLException, ClassNotFoundException, DataInsertionFailedException {
        Connection conn = null;
        int orderId;
        
        try{
            conn = DataSource.getInstance().getConnection();            
            orderId = orderDAO.createOrder(order, conn); 
        } catch(SQLException e){
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close(); 
                } catch (SQLException e) {
                    e.printStackTrace(); 
                }
            }
        }
        
        System.out.println("New order created");
        return orderId;
    }

    public int deleteOrderById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return orderDAO.deleteOrderById(id, conn);
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
    }
    public List<Order> getOrderByUserId(int userId) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return orderDAO.getOrderByUserId(userId, conn);
        }catch(SQLException e){
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally{
            if (conn != null) {
                conn.close();
            }
        }
    }
}
