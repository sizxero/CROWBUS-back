package com.sizxero.crowbus.dto.member.mypage;

import com.sizxero.crowbus.dto.drive.DriveDTO;
import lombok.*;

import java.util.List;

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
    private List<DriveDTO> drives;
}
