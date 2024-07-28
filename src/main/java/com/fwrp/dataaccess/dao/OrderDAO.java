package com.fwrp.dataaccess.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {
    
}
