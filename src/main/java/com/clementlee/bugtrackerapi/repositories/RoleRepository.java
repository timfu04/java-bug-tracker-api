package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name); // Find role by name (derived query method)

}
