package com.sizxero.crowbus.dto.post;

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
    private Long routeId;
    private String title;
    private String content;
    private Integer hit;
    private Long memberId;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
