package com.fwrp.dataaccess.dao;

import com.fwrp.models.Claim;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClaimDAO {
    int createClaim(Claim claim, Connection conn) throws SQLException;
    List<Claim> getClaimByUserId(int userId, Connection conn) throws SQLException;
    int deleteClaimById(int id,Connection conn) throws SQLException;
}
