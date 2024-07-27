package com.fwrp.dataaccess.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Subscription;

@Repository
public interface SubscriptionDAO extends JpaRepository<Subscription, Integer> {
}
