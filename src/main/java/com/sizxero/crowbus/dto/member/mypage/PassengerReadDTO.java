package com.sizxero.crowbus.dto.member.mypage;

import com.sizxero.crowbus.dto.reservation.ReservationReadDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerReadDTO {
    private String loginId;
    private String name;
    private String phone;
    private RouteDTO favoriteRoute;
    private List<ReservationReadDTO> reservations;
}
