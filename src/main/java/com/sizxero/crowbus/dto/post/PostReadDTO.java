package com.sizxero.crowbus.dto.post;

import com.sizxero.crowbus.entity.type.BoardType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReadDTO {
    private Long id;
    private Long routeId;
    private BoardType boardType;
    private String title;
    private Integer hit;
    private Long memberId;
    private LocalDateTime createTime;
}
