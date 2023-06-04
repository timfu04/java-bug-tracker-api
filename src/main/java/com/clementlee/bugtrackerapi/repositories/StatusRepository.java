package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
