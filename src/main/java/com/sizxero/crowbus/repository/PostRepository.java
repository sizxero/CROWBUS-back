package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Post;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByBoardType(BoardType bt);
    List<Post> findPostsByRoute(Route r);
    List<Post> findPostsByBoardTypeAndRoute(BoardType bt, Route r);
}
