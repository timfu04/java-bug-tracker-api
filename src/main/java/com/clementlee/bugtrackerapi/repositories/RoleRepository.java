package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
