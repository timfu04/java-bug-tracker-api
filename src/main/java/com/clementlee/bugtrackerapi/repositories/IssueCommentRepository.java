package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.IssueComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCommentRepository extends JpaRepository<IssueComment, Integer> {
}
