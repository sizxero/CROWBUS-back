package com.sizxero.crowbus.dto.member.signup;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RouteType;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PassengerDTO {
    private Long id;
    private String loginId;
    private String pw;
    private String name;
    private String phone;
    private Long favoriteRouteId;
}
