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
 * Service class for handling database operations related to orders.
 * This class provides methods to create, delete, and retrieve orders using the OrderDAO and InventoryDAO.
 * 
 * @version 1.0
 * @since 17.0.8
 * @author Ke Yan
 */
public class OrderDbService {
	
	/**
     * Data Access Object for orders.
     */
    private OrderDAO orderDAO = null;
	
	/**
     * Data Access Object for inventory.
     */
    private InventoryDAO inventoryDAO = null;
	
	/**
     * Constructor for OrderDbService.
     * Initializes the OrderDAO and InventoryDAO implementations.
     */
    public OrderDbService(){
        orderDAO = new OrderDAOImpl(); 
        inventoryDAO = new InventoryDAOImpl(); 
    }
	
	/**
     * Creates a new order.
     * 
     * @param order The order to be created.
     * @return int The ID of the newly created order.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws DataInsertionFailedException if the data insertion fails.
     */
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

	/**
     * Deletes an order by its ID.
     * 
     * @param id The ID of the order to be deleted.
     * @return int The number of rows affected by the delete operation.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
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
	
	/**
     * Retrieves a list of orders for a specific user by their user ID.
     * 
     * @param userId The ID of the user for whom the orders are to be retrieved.
     * @return {@code List<Order>} A list of orders associated with the specified user ID.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
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
