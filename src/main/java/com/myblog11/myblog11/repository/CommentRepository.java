package com.myblog11.myblog11.repository;

import com.myblog11.myblog11.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    boolean existsByPostId(long postId);
    boolean existsById(long id);
}
