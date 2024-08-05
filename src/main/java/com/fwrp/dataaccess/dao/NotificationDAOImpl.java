package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.NotificationDTO;
import com.fwrp.models.Notification;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Implementation of the NotificationDAO interface.
 * Provides methods to send notifications, retrieve notifications by user ID and method,
 * and get the count of notifications by user ID and method.
 * 
 * @version 1.0
 * @since 17.0.8
 */
public class NotificationDAOImpl implements NotificationDAO {
	
    /**
     * Sends a notification to all users subscribed to a specific method.
     * 
     * @param notification The notification message to be sent.
     * @param conn The SQL connection used to access the database.
     * @return boolean Returns true if the notification was successfully sent, false otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean sendNotifications(String notification, Connection conn) throws SQLException {
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("insert into notifications(user_id,method,date,notification)\n" +
                "SELECT user_id,method,sysdate(),? FROM subscriptions a")) {

            pstmt.setString(1, notification);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    /**
    * Retrieves a list of notifications for a specific user filtered by notification method.
    * 
    * @param userId The ID of the user for whom notifications are to be retrieved.
    * @param method The method by which the notification was sent (e.g., email, SMS).
    * @param conn The SQL connection used to access the database.
    * @return ArrayList&lt;NotificationDTO&gt; A list of {@link NotificationDTO} objects containing the notifications
    *         for the specified user and method.
    * @throws SQLException if a database access error occurs or the SQL query fails
    */
    @Override
    public ArrayList<NotificationDTO> getNotificationByUserIdAndMethod(int userId, int method, Connection conn) throws SQLException {
        ArrayList<NotificationDTO> notificationDTOs = new ArrayList<>();
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM notifications "
                + "WHERE user_id = ? AND method = ?")){
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, method);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    NotificationDTO notificationDTO = new NotificationDTO();
                    notificationDTO.setId(rs.getInt("id"));
                    notificationDTO.setUserId(rs.getInt("user_id"));
                    notificationDTO.setMethod(rs.getInt("method"));
                    notificationDTO.setNotification(rs.getString("notification"));
                    notificationDTO.setDate(rs.getDate("date"));
                    
                    notificationDTOs.add(notificationDTO);
                }
            }
        }
        
        return notificationDTOs;
    }

    /**
    * Retrieves the count of notifications for a specific user filtered by notification method.
    * 
    * @param userId The ID of the user for whom the notification count is to be retrieved.
    * @param method The method by which the notifications were sent (e.g., email, SMS).
    * @param conn The SQL connection used to access the database.
    * @return int The number of notifications for the specified user and method.
    * @throws SQLException if a database access error occurs or the SQL query fails
    */
    public int getNotifictionCountByUserIdAndMethod(int userId, int method, Connection conn) throws SQLException {
        int count = 0;
        
        try(PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) AS COUNT FROM notifications WHERE user_id = ? AND method = ?")){
            pstmt.setInt(1, userId);
            pstmt.setInt(2, method);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    count = rs.getInt("COUNT");
                    
                }
            }
        }
        return count;
    }
    
    /**
    * Inserts a notification for a user.
    * 
    * This method inserts a new notification into the database for a specified user and method.
    * It sets the current date as the notification date and uses a prepared statement to execute the SQL insert query.
    * If the insertion is successful, it returns true; otherwise, it returns false.
    * 
    * @param userId The ID of the user.
    * @param method The method of notification.
    * @param notification The notification message to be inserted.
    * @param conn The SQL connection used to access the database.
    * @return boolean Returns true if the notification was successfully inserted, false otherwise.
    * @throws SQLException if a database access error occurs
    */
    @Override
    public boolean insertNotification(int userId, int method, String notification, Connection conn) throws SQLException {
        boolean isSuccess = false;
         Date currentDate = new Date(System.currentTimeMillis());

        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO notifications (user_id, method, date, notification) VALUES (?, ?, ?, ?)")) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, method);
            pstmt.setDate(3, currentDate);
            pstmt.setString(4, notification);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }
}
