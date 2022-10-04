package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
