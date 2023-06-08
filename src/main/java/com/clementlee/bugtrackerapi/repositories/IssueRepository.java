package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

    List<Issue> findByUsersAssignedId(int userId);

}
