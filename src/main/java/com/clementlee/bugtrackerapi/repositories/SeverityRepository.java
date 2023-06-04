package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Severity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeverityRepository extends JpaRepository<Severity, Integer> {
}
