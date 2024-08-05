package com.fwrp.dataaccess.dao;

import com.fwrp.constants.UserTypeConstant;
import com.fwrp.dataaccess.dto.PreferenceDTO;
import com.fwrp.models.Consumer;
import com.fwrp.models.Food;
import com.fwrp.models.Preference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the SubscriptionDAO interface.
 * Provides methods to add a subscription, retrieve all subscription methods by user ID, and delete a subscription.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public class PreferenceDAOImpl implements PreferenceDAO {
    
    /**
    * Adds a new preference for a user.
    * 
    * @param userId The ID of the user.
    * @param foodId The ID of the food item.
    * @param conn The SQL connection used to access the database.
    * @return boolean True if the preference was added successfully, false otherwise.
    * @throws SQLException if a database access error occurs.
    */
    @Override
    public boolean addPreference(int userId,int foodId , Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("insert into preferences(user_id,food_id) values(?,?)")) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, foodId);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    /**
     * Retrieves all subscription methods for a specific user.
     * 
     * @param userId The ID of the user.
     * @param conn The SQL connection used to access the database.
     * @return List&lt;SubscriptionDTO&gt; A list of {@link SubscriptionDTO} objects containing the subscription methods for the specified user.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Preference> getAllFoodIdByUserId(int userId, Connection conn) throws SQLException {
        List<Preference> preferences = new ArrayList<>();
        String sql = "select * from preferences as p left join foods on p.food_id = foods.id left join users on p.user_id = users.id where users.id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, userId);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Food food = new Food();
                    food.setId(rs.getInt("food_id"));
                    food.setName(rs.getString("name"));
                    food.setDiscount(rs.getDouble("discount"));
                    food.setExpireDays(rs.getInt("expire_days"));
                    food.setUnitPrice(rs.getDouble("unitprice"));
                    Consumer consumer = new Consumer();
                    consumer.setId(rs.getInt("user_id"));
                    consumer.setFirstName(rs.getString("firstname"));
                    consumer.setLastName(rs.getString("lastname"));
                    
                    Preference preference = new Preference();
                    preference.setId(rs.getInt("id"));
                    preference.setFood(food);
                    preference.setConsumer(consumer);
                    
                    preferences.add(preference);
                }
            }
        }

        return preferences;
    }

    /**
     * Deletes a subscription by its ID.
     * 
     * @param id The ID of the subscription to be deleted.
     * @param conn The SQL connection used to access the database.
     * @return boolean True if the subscription was deleted successfully, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean deletePreference(int id, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("delete from preferences where id=?")) {

            pstmt.setInt(1, id);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }
    
    /**
     * Retrieves the subscription methods for charities.
     * 
     * @param conn The SQL connection used to access the database.
     * @return Map<Integer, Integer> A map of charity IDs to their subscription methods.
     * @throws SQLException if a database access error occurs
     */
    public Map<Integer, Integer> getCharitySubscribeInfo(Connection conn) throws SQLException{
        Map<Integer, Integer> map = new HashMap<>();
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT user_id, method "
                + "FROM subscriptions as s "
                + "LEFT JOIN users ON s.user_id = users.id "
                + "WHERE type = " + UserTypeConstant.CHARITY)){
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    int method = rs.getInt("method");
                    
                    map.put(userId, method);
                }
            }
            
        }
            
        return map;
    }
    
    /**
     * Retrieves the subscription methods for consumers by food ID.
     * 
     * @param foodId The ID of the food item.
     * @param conn The SQL connection used to access the database.
     * @return Map<Integer, Integer> A map of consumer IDs to their subscription methods.
     * @throws SQLException if a database access error occurs
     */
    public Map<Integer, Integer> getConsumerSubscribeInfo(int foodId,Connection conn) throws SQLException{
        Map<Integer, Integer> map = new HashMap<>();
         try(PreparedStatement pstmt = conn.prepareStatement("SELECT s.user_id, method "
                 + "FROM subscriptions as s "
                 + "LEFT JOIN users on s.user_id = users.id "
                 + "LEFT JOIN preferences as p on s.user_id = p.user_id "
                 + "WHERE food_id = ? "
                 + "AND type = " + UserTypeConstant.CONSUMER)){
            
            pstmt.setInt(1, foodId);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    int method = rs.getInt("method");
                    
                    map.put(userId, method);
                }
            }
            
        }
            
        return map;
    }
}
