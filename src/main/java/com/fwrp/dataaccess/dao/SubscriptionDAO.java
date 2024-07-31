package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface SubscriptionDAO{
    boolean addSubscription(int userId,int method , Connection conn)  throws SQLException;
    List<SubscriptionDTO> getAllMethodsByUserId(int userId, Connection conn) throws SQLException;
    boolean deleteSubscription(int id, Connection conn)  throws SQLException;
}
