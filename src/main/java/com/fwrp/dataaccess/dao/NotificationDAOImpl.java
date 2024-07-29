package com.fwrp.dataaccess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotificationDAOImpl implements NotificationDAO {
    /**
     * @param notification
     * @return
     * @throws SQLException
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
}
