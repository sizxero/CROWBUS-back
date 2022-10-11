package com.sizxero.crowbus.dto.post;

import com.sizxero.crowbus.dto.route.RouteDTO;
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
    private RouteDTO route;
    private BoardType boardType;
    private String title;
    private Integer hit;
    private String memberLoginId;
    private LocalDateTime modifiedTime;
}
