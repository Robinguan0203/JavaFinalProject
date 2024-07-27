package com.fwrp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.ExpireInfo;

@Repository
public interface ExpireInfoDAO extends JpaRepository<ExpireInfo, Integer> {
}
