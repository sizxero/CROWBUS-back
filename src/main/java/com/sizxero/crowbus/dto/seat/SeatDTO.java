package com.sizxero.crowbus.dto.seat;

import com.sizxero.crowbus.entity.type.SeatType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDTO {
    private Long id;
    private LocalDate date;
    private Integer seatNo;
    private SeatType seatType;
    private Long driveId;
}
