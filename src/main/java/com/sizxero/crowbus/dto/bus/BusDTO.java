package com.sizxero.crowbus.dto.bus;

import com.sizxero.crowbus.entity.Bus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusDTO {
    private Long id;
    private String busNum;
    private Integer capacity;
}
