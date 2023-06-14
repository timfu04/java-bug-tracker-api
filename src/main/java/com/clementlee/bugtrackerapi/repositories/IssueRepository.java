package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
}
