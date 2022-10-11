package com.sizxero.crowbus.dto.post;

import com.sizxero.crowbus.entity.type.BoardType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDTO {
    private BoardType boardType;
    private String title;
    private String contents;
    private String memberLoginId;
    private Long routeId;
}
