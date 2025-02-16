/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService.notificationcountobserver;

import com.fwrp.dbService.notificationcountobserver.NotificationCountObserver;
import com.fwrp.constants.NotificationMethodConstant;
import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.NotificationDAO;
import com.fwrp.dataaccess.dao.NotificationDAOImpl;
import com.fwrp.models.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of NotificationCountObserver for phone notifications.
 * This class provides methods to retrieve the count of phone notifications for a given user.
 * 
 * @version 1.0
 * @since 17.0.8 
 * @author Robin Guan
 */
public class PhoneNotificationCountObserver implements NotificationCountObserver {
	
	/**
     * Data Access Object for notifications.
     */
    private NotificationDAO notificationDAO;
    
	/**
     * Constructor for PhoneNotificationCountObserver.
     * Initializes the NotificationDAO implementation.
     */
    public PhoneNotificationCountObserver(){
        notificationDAO = new NotificationDAOImpl();
    }
    
	/**
     * Retrieves the count of phone notifications for a given user.
     * 
     * @param user The user for whom the notification count is to be retrieved.
     * @return int The count of phone notifications for the specified user.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     */
    @Override
    public int getNotificationCount(User user) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        int count = 0;
        
        try{
            conn = DataSource.getInstance().getConnection();
            count = notificationDAO.getNotifictionCountByUserIdAndMethod(user.getId(), NotificationMethodConstant.PHONE, conn);
        }catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
     
        return count;
    }
}
