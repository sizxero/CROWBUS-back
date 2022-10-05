package com.sizxero.crowbus.dto.seat;

import com.sizxero.crowbus.entity.type.SeatType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatUpdateDTO {
    private Long id;
    private SeatType seatType;
}
