package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Post;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByBoardType(BoardType bt, Pageable pageable);
    Page<Post> findByBoardTypeAndRoute(BoardType bt, Route r, Pageable pageable);
}
