package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
