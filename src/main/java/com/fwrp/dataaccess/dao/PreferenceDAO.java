package com.fwrp.dataaccess.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fwrp.models.Preference;

@Repository
public interface PreferenceDAO extends JpaRepository<Preference, Integer> {
}
