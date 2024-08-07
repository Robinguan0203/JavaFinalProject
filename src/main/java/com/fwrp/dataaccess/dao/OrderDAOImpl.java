package com.fwrp.dataaccess.dao;

import com.fwrp.models.Order;
import com.fwrp.models.Food;
import com.fwrp.models.Consumer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the OrderDAO interface.
 * Provides methods to create an order, retrieve orders by user ID, and delete an order by ID.
 * 
 * @author Ke Yan
 * @version 2.0
 */
public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER_SQL = "INSERT INTO orders (user_id, food_id, date, unitprice, discount, quantity) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_ORDER_SQL = "delete from orders where id=?";
    private static final String SELECT_ORDERS_BY_USER_ID_SQL = "SELECT t1.id, "
            + "t1.user_id, t1.food_id, t1.date, t1.quantity, t2.discount, "
            + "t2.expire_days, t2.name as food_name, t3.firstname, t3.lastname, t3.phone, "
            + "t3.email, t3.type, t3.organization, t3.password, "
            + "t2.unitprice "
            + "FROM orders t1, foods t2, users t3 "
            + "WHERE user_id = ? and t1.food_id = t2.id and t1.user_id = t3.id";

    private InventoryDAO inventoryDAO = new InventoryDAOImpl();
    private ExpireInfoDAO expireInfoDAO = new ExpireInfoDAOImpl();

    /**
     * Creates a new order in the database.
     * 
     * @param order The {@link Order} object containing the order details.
     * @param conn The SQL connection used to access the database.
     * @return int The ID of the newly created order.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public int createOrder(Order order, Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, order.getConsumer().getId());
            pstmt.setInt(2, order.getFood().getId());
            pstmt.setDate(3, new java.sql.Date(order.getDate().getTime()));
            pstmt.setDouble(4, order.getFood().getUnitPrice());
            pstmt.setDouble(5, order.getFood().getDiscount());
            pstmt.setInt(6, order.getQtyDiscount());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }
    }

    /**
     * Deletes an order by its ID.
     * 
     * @param id The ID of the order to be deleted.
     * @param conn The SQL connection used to access the database.
     * @return int The number of rows affected by the delete operation.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public int deleteOrderById(int id, Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(DELETE_ORDER_SQL)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }

    /**
     * Retrieves a list of orders for a specific user.
     * 
     * @param userId The ID of the user for whom orders are to be retrieved.
     * @param conn The SQL connection used to access the database.
     * @return List&lt;Order&gt; A list of {@link Order} objects containing the orders for the specified user.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Order> getOrderByUserId(int userId, Connection conn) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(SELECT_ORDERS_BY_USER_ID_SQL)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Food food = new Food();
                    food.setId(rs.getInt("food_id"));
                    food.setName(rs.getString("food_name"));
                    food.setDiscount(rs.getDouble("discount"));
                    food.setUnitPrice(rs.getDouble("unitprice"));
                    food.setExpireDays(rs.getInt("expire_days"));

                    Consumer consumer = new Consumer();
                    consumer.setFirstName(rs.getString("firstname"));
                    consumer.setLastName(rs.getString("lastname"));
                    consumer.setPhone(rs.getString("phone"));
                    consumer.setEmail(rs.getString("email"));
                    consumer.setPassword(rs.getString("password"));
                    consumer.setType(rs.getInt("type"));
                    consumer.setOrganization(rs.getString("organization"));

                    Order order = new Order(
                        rs.getInt("id"),
                        consumer,
                        food,
                        rs.getDate("date"),
                        rs.getInt("quantity")
                    );
                    orderList.add(order);
                }
            }
        }
        return orderList;
    }
}
