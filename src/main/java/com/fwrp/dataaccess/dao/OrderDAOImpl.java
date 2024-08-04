package com.fwrp.dataaccess.dao;

import com.fwrp.models.Order;
import com.fwrp.models.Food;
import com.fwrp.models.Inventory;
import com.fwrp.models.ExpireInfo;
import com.fwrp.dataaccess.DataSource;
import com.fwrp.models.Consumer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String INSERT_ORDER_SQL = "INSERT INTO orders (user_id, food_id, date, quantity) VALUES (?, ?, ?, ?)";
    private static final String DELETE_ORDER_SQL = "delete from orders where id=?";
    private static final String SELECT_ORDERS_BY_USER_ID_SQL = "SELECT t1.id,"
            + "t1.user_id,t1.food_id,t1.date,t1.quantity,t2.discount,"
            + "t2.expire_days,t2.name as food_name,t3.firstname, t3.lastname, t3.phone, "
            + "t3.email, t3.type, t3.organization, t3.password,"
            + "t2.unitprice "
            + "FROM orders t1,foods t2, users t3 "
            + "WHERE user_id = ? and t1.food_id = t2.id and t1.user_id = t3.id";

    private InventoryDAO inventoryDAO = new InventoryDAOImpl();
    private ExpireInfoDAO expireInfoDAO = new ExpireInfoDAOImpl();

    @Override
    public int createOrder(Order order, Connection conn) throws SQLException {
        try(PreparedStatement pstmt = conn.prepareStatement(INSERT_ORDER_SQL,Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, order.getConsumer().getId());
            pstmt.setInt(2, order.getFood().getId());
            pstmt.setDate(3,new java.sql.Date(order.getDate().getTime()));
            pstmt.setInt(4, order.getQtyDiscount());
            
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

    public int deleteOrderById(int id,Connection conn) throws SQLException{

        try(PreparedStatement pstmt = conn.prepareStatement(DELETE_ORDER_SQL)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate();
        }

    }
    @Override
    public List<Order> getOrderByUserId(int userId, Connection conn)  throws SQLException{
        List<Order> orderList= new ArrayList<>();
        Order order = null;
        Food food=null;
        Consumer consumer = null;
        try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ORDERS_BY_USER_ID_SQL)){            
            pstmt.setInt(1, userId);
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    food=new Food();
                    food.setId(rs.getInt("food_id"));
                    food.setName(rs.getString("food_name"));
                    food.setDiscount(rs.getDouble("discount"));
                    food.setUnitPrice(rs.getDouble("unitprice"));
                    food.setExpireDays(rs.getInt("expire_days"));
                    consumer = new Consumer();
                    consumer.setFirstName(rs.getString("firstname"));
                    consumer.setLastName(rs.getString("lastname"));
                    consumer.setPhone(rs.getString("phone"));
                    consumer.setEmail(rs.getString("email"));
                    consumer.setPassword(rs.getString("password"));
                    consumer.setType(rs.getInt("type"));
                    consumer.setOrganization(rs.getString("organization"));
                    order = new Order(
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
