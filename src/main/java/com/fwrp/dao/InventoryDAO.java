package com.fwrp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Inventory;

@Repository
public interface InventoryDAO extends JpaRepository<Inventory, Integer> {
}
