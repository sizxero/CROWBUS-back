package com.sizxero.crowbus.controller;


import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.member.LoginDTO;
import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.security.TokenProvider;
import com.sizxero.crowbus.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService service;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Member entity = service.getByCredentials(dto.getLoginId(), dto.getPw());
        if(entity != null) {
            final String token = tokenProvider.create(entity);
            final LoginDTO resdto = LoginDTO.builder()
                    .token(token)
                    .loginId(entity.getLoginId())
                    .role(entity.getRoleType())
                    .build();
            return ResponseEntity.ok().body(resdto);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder().error("Login failed").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
