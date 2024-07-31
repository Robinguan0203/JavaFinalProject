package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.SubscriptionDAO;
import com.fwrp.dataaccess.dao.SubscriptionDAOImpl;
import com.fwrp.dataaccess.dto.SubscriptionDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionDbService {
    private SubscriptionDAO subscriptionDAO;
    public SubscriptionDbService(){
        subscriptionDAO = new SubscriptionDAOImpl();
    }

    public boolean addSubscription(int userId,int method) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.addSubscription(userId,method, conn);
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

    public boolean deleteSubscription(int id) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.deleteSubscription(id, conn);
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

    public List<SubscriptionDTO> getAllMethodsByUserId(int userId) throws SQLException, ClassNotFoundException{
        Connection conn = null;

        try{
            conn = DataSource.getInstance().getConnection();
            return subscriptionDAO.getAllMethodsByUserId(userId, conn);
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
