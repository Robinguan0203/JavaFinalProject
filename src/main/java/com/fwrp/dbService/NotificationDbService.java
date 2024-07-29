package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.NotificationDAO;
import com.fwrp.dataaccess.dao.NotificationDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class NotificationDbService {
    private NotificationDAO notificationDAO;
    public NotificationDbService(){
        notificationDAO = new NotificationDAOImpl();
    }

    public boolean sendNotification(String notification) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return notificationDAO.sendNotifications(notification, conn);
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
