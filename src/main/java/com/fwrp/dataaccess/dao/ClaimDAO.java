package com.fwrp.dataaccess.dao;

import com.fwrp.models.Claim;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClaimDAO {
    boolean createClaim(Claim claim, Connection conn) throws SQLException;
    Claim getClaimByUserId(int userId, Connection conn) throws SQLException;
}
