package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.PagingDTO;
import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.member.MemberDTO;
import com.sizxero.crowbus.dto.member.signup.PassengerDTO;
import com.sizxero.crowbus.dto.post.PostCreateDTO;
import com.sizxero.crowbus.dto.post.PostPagingDTO;
import com.sizxero.crowbus.dto.post.PostReadDTO;
import com.sizxero.crowbus.dto.post.PostReadDetailDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.Post;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.BoardType;
import com.sizxero.crowbus.service.MemberService;
import com.sizxero.crowbus.service.PostService;
import com.sizxero.crowbus.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> createPost(@AuthenticationPrincipal String id, @RequestBody PostCreateDTO dto) {
        try {
            Optional<Post> result = postService.createPost(
                    Post.builder()
                            .hit(0)
                            .boardType(dto.getBoardType())
                            .title(dto.getTitle())
                            .content(dto.getContents())
                            .member(memberService.readMemberByLoginId(id).get())
                            .route(routeService.readOneRouteById(dto.getRouteId()).get())
                            .build()
            );
            return ResponseEntity.ok()
                    .body(ResponseDTO.<PostReadDetailDTO>builder()
                            .data(result.stream().map(v->
                                    PostReadDetailDTO.builder()
                                            .id(v.getId())
                                            .boardType(v.getBoardType())
                                            .route(RouteDTO.builder()
                                                    .id(v.getRoute().getId())
                                                    .name(v.getRoute().getName())
                                                    .routeType(v.getRoute().getRouteType().name())
                                                    .build())
                                            .member(MemberDTO.builder()
                                                    .id(v.getMember().getId())
                                                    .name(v.getMember().getName())
                                                    .loginId(v.getMember().getLoginId())
                                                    .build())
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
    public ResponseEntity<?>readPosts(@RequestParam String board,
                                       @RequestParam(required = false) String rtId,
                                       @RequestParam(required = false) String page) {

        log.info("보드:" + board + ",루트:" + rtId + ",페이지: " + page);
        try {
            if(page == null || page.equals("") || page.equals("null"))
                page="0";
            else
                page= String.valueOf(Integer.parseInt(page)-1);


            Page<Post> resultPage;
            if (rtId == null || rtId.equals("") || rtId.equals("null")) {
                resultPage = postService.readPostsByBoardTypePageable(BoardType.values()[Integer.parseInt(board)].name(), page);
            } else {
                resultPage = postService.readPostsByBoardTypeAndRoutePageable(BoardType.values()[Integer.parseInt(board)].name(), rtId, page);
            }
            log.info("리스폰스" + resultPage);
            List<PostReadDTO> dtos = resultPage.stream().map(v ->
                    PostReadDTO.builder()
                            .id(v.getId())
                            .boardType(v.getBoardType())
                            .title(v.getTitle())
                            .hit(v.getHit())
                            .memberLoginId(v.getMember().getLoginId())
                            .modifiedTime(v.getModifiedTime())
                            .route(RouteDTO.builder()
                                    .id(v.getRoute().getId())
                                    .name(v.getRoute().getName())
                                    .routeType(v.getRoute().getRouteType().name())
                                    .build())
                            .build()).toList();
            PagingDTO pageInfo = PagingDTO.builder()
                    .number(resultPage.getNumber())
                    .size(resultPage.getSize())
                    .totalPage(resultPage.getTotalPages())
                    .build();

            return ResponseEntity.ok().body(ResponseDTO.<PostPagingDTO>builder()
                            .data(resultPage.stream().map(v ->
                                    PostPagingDTO.builder()
                                            .posts(dtos)
                                            .page(pageInfo)
                                            .build()
                            ).toList())
                    .build());

        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
                                            .route(RouteDTO.builder()
                                                    .id(v.getRoute().getId())
                                                    .name(v.getRoute().getName())
                                                    .routeType(v.getRoute().getRouteType().name())
                                                    .build())
                                            .member(MemberDTO.builder()
                                                    .id(v.getMember().getId())
                                                    .name(v.getMember().getName())
                                                    .loginId(v.getMember().getLoginId())
                                                    .build())
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
