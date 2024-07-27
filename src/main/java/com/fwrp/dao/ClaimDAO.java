package com.fwrp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Claim;

@Repository
public interface ClaimDAO extends JpaRepository<Claim, Integer> {
}
