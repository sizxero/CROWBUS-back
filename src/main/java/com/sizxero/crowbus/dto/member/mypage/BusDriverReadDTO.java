package com.sizxero.crowbus.dto.member.mypage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusDriverReadDTO {
    private String loginId;
    private String name;
    private String phone;
    private String license;
}
