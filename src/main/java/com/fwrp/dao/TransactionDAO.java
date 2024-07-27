package com.fwrp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Transaction;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer> {
}
