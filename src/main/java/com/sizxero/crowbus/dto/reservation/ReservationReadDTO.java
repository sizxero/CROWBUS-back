package com.sizxero.crowbus.dto.reservation;

import com.sizxero.crowbus.dto.seat.SeatDTO;
import com.sizxero.crowbus.dto.timetable.TimetableDTO;
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
    private SeatDTO seat;
    private TimetableDTO place;
    private ReservationType reservationType;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}