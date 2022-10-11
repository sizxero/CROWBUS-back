package com.sizxero.crowbus.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingDTO {
    private Integer number;
    private Integer size;
    private Integer totalPage;
}
