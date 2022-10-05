package com.sizxero.crowbus.dto.reservation;

import com.sizxero.crowbus.entity.type.ReservationType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationUpdateDTO {
    private Long rvId;
    private Long seatId;
    private ReservationType rvType;
}
