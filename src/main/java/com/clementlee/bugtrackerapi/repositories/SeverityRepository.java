package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Severity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeverityRepository extends JpaRepository<Severity, Integer> {

    Optional<Severity> findByName(String name); // Find severity by name (derived query method)

}
