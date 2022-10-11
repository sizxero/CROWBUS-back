package com.sizxero.crowbus.dto.post;

import com.sizxero.crowbus.dto.PagingDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostPagingDTO {
    private List<PostReadDTO> posts;
    private PagingDTO page;
}
