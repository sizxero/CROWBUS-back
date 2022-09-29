package com.sizxero.crowbus.dto.member.signup;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.member.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PassengerDTO {
    private Long id;
    private String loginId;
    private String pw;
    private String name;
    private String phone;
    private String favoriteRoute;

    public PassengerDTO(final Passenger entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.pw = entity.getPw();
        this.name = entity.getPw();
        this.phone = entity.getPhone();
        this.favoriteRoute = entity.getFavoriteRoute();
    }

    public static Passenger toEntity(final PassengerDTO dto){
        return Passenger.builder()
                .id(dto.getId())
                .loginId(dto.getLoginId())
                .pw(dto.getPw())
                .name(dto.getName())
                .phone(dto.getPhone())
                .favoriteRoute(dto.getFavoriteRoute())
                .build();
    }
}
