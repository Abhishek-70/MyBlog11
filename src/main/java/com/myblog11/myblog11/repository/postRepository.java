package com.myblog11.myblog11.repository;

import com.myblog11.myblog11.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<Post,Long> {

}
