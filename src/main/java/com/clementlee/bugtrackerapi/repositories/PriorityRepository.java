package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
