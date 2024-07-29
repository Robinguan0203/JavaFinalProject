package com.fwrp.dataaccess.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

@Repository
public interface NotificationDAO{
    boolean sendNotifications(String notification ,Connection conn)  throws SQLException;
}
