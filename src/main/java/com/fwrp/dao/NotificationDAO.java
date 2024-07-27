package com.fwrp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Notification;

@Repository
public interface NotificationDAO extends JpaRepository<Notification, Integer> {
}
