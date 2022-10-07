package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.member.signup.BusDriverDTO;
import com.sizxero.crowbus.dto.member.signup.PassengerDTO;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RoleType;
import com.sizxero.crowbus.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<?> readOneMember(@AuthenticationPrincipal String id) {
        try {
            if (memberService.findRoleTypeByLoginId(id).equals(RoleType.PASSENGER)) {
                Optional<Passenger> result = memberService.readOnePassengerByLoginId(id);
                List<PassengerDTO> dtos = result.stream().map(v ->
                        PassengerDTO.builder()
                                .id(v.getId())
                                .loginId(v.getLoginId())
                                .name(v.getName())
                                .phone(v.getPhone())
                                .favoriteRouteId(v.getFavoriteRoute() == null ? null : v.getFavoriteRoute().getId())
                                .build()).toList();
                return ResponseEntity.ok().body(
                        ResponseDTO.<PassengerDTO>builder()
                                .data(dtos)
                                .build()
                );
            } else if(memberService.findRoleTypeByLoginId(id).equals(RoleType.BUSDRIVER)) {
                Optional<BusDriver> result = memberService.readOneBusDriverByLoginId(id);
                List<BusDriverDTO> dtos = result.stream().map(v ->
                        BusDriverDTO.builder()
                                .id(v.getId())
                                .loginId(v.getLoginId())
                                .name(v.getName())
                                .phone(v.getPhone())
                                .driverLicenseNo(v.getDriverLicenseNo())
                                .build()).toList();
                return ResponseEntity.ok().body(
                        ResponseDTO.<BusDriverDTO>builder()
                                .data(dtos)
                                .build()
                );
            } else {
                return ResponseEntity.badRequest().body("미구현");
            }
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
