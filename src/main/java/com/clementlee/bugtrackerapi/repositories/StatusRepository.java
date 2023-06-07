package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Optional<Status> findByName(String name); // Find status by name (derived query method)

}
