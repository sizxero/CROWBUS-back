package com.sizxero.crowbus.dto.reservation;

import com.sizxero.crowbus.entity.type.ReservationType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationReadDTO {
    private Long passengerId;
    private Long seatId;
    private ReservationType reservationType;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}