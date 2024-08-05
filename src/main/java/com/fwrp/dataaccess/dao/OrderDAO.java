package com.fwrp.dataaccess.dao;

import com.fwrp.models.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines the standard operations to be performed on Order model object(s).
 * It provides methods to create an order, retrieve orders by user ID, and delete an order by ID.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public interface OrderDAO {
	/**
     * Creates a new order.
     * 
     * @param order The {@link Order} object containing the order details.
     * @param conn The SQL connection used to access the database.
     * @return int The ID of the newly created order.
     * @throws SQLException if a database access error occurs
     */
    int createOrder(Order order, Connection conn) throws SQLException;
	
	/**
     * Retrieves a list of orders for a specific user.
     * 
     * @param userId The ID of the user for whom orders are to be retrieved.
     * @param conn The SQL connection used to access the database.
     * @return List&lt;Order&gt; A list of {@link Order} objects containing the orders for the specified user.
     * @throws SQLException if a database access error occurs
     */
    List<Order> getOrderByUserId(int userId, Connection conn) throws SQLException;
	
	/**
     * Deletes an order by its ID.
     * 
     * @param id The ID of the order to be deleted.
     * @param conn The SQL connection used to access the database.
     * @return int The number of rows affected by the delete operation.
     * @throws SQLException if a database access error occurs
     */
    int deleteOrderById(int id,Connection conn) throws SQLException;
}
