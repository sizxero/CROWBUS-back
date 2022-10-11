package com.sizxero.crowbus.dto.post;

import com.sizxero.crowbus.dto.member.MemberDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.entity.type.BoardType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReadDetailDTO {
    private Long id;
    private BoardType boardType;
    private RouteDTO route;
    private String title;
    private String content;
    private Integer hit;
    private MemberDTO member;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
