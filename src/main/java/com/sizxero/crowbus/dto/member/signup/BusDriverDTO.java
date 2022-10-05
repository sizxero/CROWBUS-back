package com.sizxero.crowbus.dto.member.signup;

import com.sizxero.crowbus.entity.member.BusDriver;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusDriverDTO {
    private Long id;
    private String loginId;
    private String pw;
    private String name;
    private String phone;
    private String driverLicenseNo;
}
