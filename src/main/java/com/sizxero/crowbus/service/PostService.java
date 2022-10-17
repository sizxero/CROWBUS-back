package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Post;
import com.sizxero.crowbus.entity.type.BoardType;
import com.sizxero.crowbus.repository.PostRepository;
import com.sizxero.crowbus.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RouteRepository routeRepository;

    public Optional<Post> createPost(final Post entity) {
        validate(entity);
        postRepository.save(entity);
        return postRepository.findById(entity.getId());
    }

    public List<Post> readAllPosts() { return postRepository.findAll(); }
//    public List<Post> readPostsByBoard(BoardType bt) {
//        return postRepository.findPostsByBoardType(bt);
//    }
//    public List<Post> readPostsByRoute(String rId) {
//        return postRepository.findPostsByRoute(routeRepository.findById(Long.parseLong(rId)).get());
//    }
//    public List<Post> readPostsByBoardAndRoute(BoardType bt, String rId) {
//        return postRepository.findPostsByBoardTypeAndRoute(bt, routeRepository.findById(Long.parseLong(rId)).get());
//    }

    public Optional<Post> readOnePost(String id) {
        return postRepository.findById(Long.parseLong(id));
    }

    private void validate(final Post entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
    }

    public Page<Post> readPostsByBoardTypePageable(String bt, String page) {
        PageRequest pr = PageRequest.of(Integer.parseInt(page), 10);
        log.info("서비스" + pr.toString());
        return postRepository.findByBoardType(BoardType.valueOf(bt), pr);
    }

    public Page<Post> readPostsByBoardTypeAndRoutePageable(String bt, String rId, String page) {
        PageRequest pr = PageRequest.of(Integer.parseInt(page), 10);
        return postRepository.findByBoardTypeAndRouteOrderByIdDesc(BoardType.valueOf(bt),
                routeRepository.findById(Long.parseLong(rId)).get(), pr);
    }
}
