package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.member.signup.PassengerDTO;
import com.sizxero.crowbus.dto.post.PostCreateDTO;
import com.sizxero.crowbus.dto.post.PostReadDTO;
import com.sizxero.crowbus.dto.post.PostReadDetailDTO;
import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.Post;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.BoardType;
import com.sizxero.crowbus.service.MemberService;
import com.sizxero.crowbus.service.PostService;
import com.sizxero.crowbus.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("posting")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCreateDTO dto) {
        try {
            Optional<Post> result = postService.createPost(
                    Post.builder()
                            .hit(0)
                            .boardType(dto.getBoardType())
                            .title(dto.getTitle())
                            .content(dto.getContents())
                            .member(memberService.readOneMember(dto.getMemberId()).get())
                            .route(routeService.readOneRouteById(dto.getRouteId()).get())
                            .build()
            );
            return ResponseEntity.ok()
                    .body(ResponseDTO.<PostReadDetailDTO>builder()
                            .data(result.stream().map(v->
                                    PostReadDetailDTO.builder()
                                            .id(v.getId())
                                            .boardType(v.getBoardType())
                                            .routeId(v.getRoute().getId())
                                            .memberId(v.getMember().getId())
                                            .hit(v.getHit())
                                            .createTime(v.getCreateTime())
                                            .modifiedTime(v.getModifiedTime())
                                            .title(v.getTitle())
                                            .content(v.getContent())
                                            .build()
                            ).toList())
                            .build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

    @GetMapping
    public ResponseEntity<?> readPosts(@RequestParam(required = false) String board, @RequestParam(required = false) String rtId) {
        try {
            List<Post> result;
            if(!(board == null || board.equals("")) && !(rtId == null || rtId.equals(""))) {
                result = postService.readPostsByBoardAndRoute(BoardType.values()[Integer.parseInt(board)], rtId);
            } else {
                if(board == null || board.equals(""))
                    result = postService.readPostsByRoute(rtId);
                else
                    result = postService.readPostsByBoard(BoardType.values()[Integer.parseInt(board)]);
            }
            if((board == null || board.equals("")) && (rtId == null || rtId.equals(""))) {
                result = postService.readAllPosts();
            }

            return ResponseEntity.ok()
                    .body(ResponseDTO.<PostReadDetailDTO>builder()
                            .data(result.stream().map(v->
                                    PostReadDetailDTO.builder()
                                            .id(v.getId())
                                            .boardType(v.getBoardType())
                                            .routeId(v.getRoute().getId())
                                            .memberId(v.getMember().getId())
                                            .hit(v.getHit())
                                            .createTime(v.getCreateTime())
                                            .modifiedTime(v.getModifiedTime())
                                            .title(v.getTitle())
                                            .content(v.getContent())
                                            .build()
                            ).toList())
                            .build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readPost(@PathVariable String id) {
        try {
            Optional<Post> result = postService.readOnePost(id);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<PostReadDetailDTO>builder()
                            .data(result.stream().map(v->
                                    PostReadDetailDTO.builder()
                                            .id(v.getId())
                                            .boardType(v.getBoardType())
                                            .routeId(v.getRoute().getId())
                                            .memberId(v.getMember().getId())
                                            .hit(v.getHit())
                                            .createTime(v.getCreateTime())
                                            .modifiedTime(v.getModifiedTime())
                                            .title(v.getTitle())
                                            .content(v.getContent())
                                            .build()
                            ).toList())
                            .build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }
}