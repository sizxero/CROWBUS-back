package com.sizxero.crowbus.dto.member.signup;

import com.sizxero.crowbus.entity.member.BusDriver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusDriverDTO {
    private Long id;
    private String loginId;
    private String pw;
    private String name;
    private String phone;
    private String driverLicenseNo;

    public BusDriverDTO(final BusDriver entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.pw = entity.getPw();
        this.name = entity.getPw();
        this.phone = entity.getPhone();
        this.driverLicenseNo = entity.getDriverLicenseNo();
    }

    public static BusDriver toEntity(final BusDriverDTO dto){
        return BusDriver.builder()
                .id(dto.getId())
                .loginId(dto.getLoginId())
                .pw(dto.getPw())
                .name(dto.getName())
                .phone(dto.getPhone())
                .driverLicenseNo(dto.getDriverLicenseNo())
                .build();
    }
}
