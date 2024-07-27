package com.fwrp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Food;

@Repository
public interface FoodDAO extends JpaRepository<Food, Integer> {
}
