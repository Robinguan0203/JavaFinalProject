package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.NotificationDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public interface NotificationDAO{
    boolean sendNotifications(String notification ,Connection conn)  throws SQLException;
    ArrayList<NotificationDTO> getNotificationByUserIdAndMethod(int userId, int method, Connection conn) throws SQLException;
    int getNotifictionCountByUserIdAndMethod(int userId, int method, Connection conn) throws SQLException;
}
