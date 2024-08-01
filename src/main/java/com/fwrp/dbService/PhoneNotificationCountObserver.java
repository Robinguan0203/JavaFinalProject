/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.constants.NotificationMethodConstant;
import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.NotificationDAO;
import com.fwrp.dataaccess.dao.NotificationDAOImpl;
import com.fwrp.models.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author robin
 */
public class PhoneNotificationCountObserver implements NotificationCountObserver {
    private NotificationDAO notificationDAO;
    
    public PhoneNotificationCountObserver(){
        notificationDAO = new NotificationDAOImpl();
    }
    
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
