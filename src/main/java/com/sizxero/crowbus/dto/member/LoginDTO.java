package com.sizxero.crowbus.dto.member;

import com.sizxero.crowbus.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private String token;
    private String loginId;
    private String pw;
}
