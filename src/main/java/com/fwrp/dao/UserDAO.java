package com.fwrp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
