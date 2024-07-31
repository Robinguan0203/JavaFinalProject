package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.SubscriptionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionDAO {

    @Override
    public boolean addSubscription(int userId,int method , Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("insert into subscriptions(user_id,method) values(?,?)")) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, method);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }


    @Override
    public List<SubscriptionDTO> getAllMethodsByUserId(int userId, Connection conn) throws SQLException {
        List<SubscriptionDTO> subscriptionDTOS = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM subscriptions "
                + "WHERE user_id = ?")){

            pstmt.setInt(1, userId);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
                    subscriptionDTO.setId(rs.getInt("id"));
                    subscriptionDTO.setUserId(rs.getInt("user_id"));
                    subscriptionDTO.setMethod(rs.getInt("method"));

                    subscriptionDTOS.add(subscriptionDTO);
                }
            }
        }

        return subscriptionDTOS;
    }

    @Override
    public boolean deleteSubscription(int id, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement("delete from subscriptions where id=?")) {

            pstmt.setInt(1, id);
            if(pstmt.executeUpdate() >0){
                isSuccess = true;
            }
        }

        return isSuccess;
    }

}