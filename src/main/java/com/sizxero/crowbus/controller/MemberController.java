package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.member.mypage.BusDriverReadDTO;
import com.sizxero.crowbus.dto.member.mypage.PassengerReadDTO;
import com.sizxero.crowbus.dto.member.signup.BusDriverDTO;
import com.sizxero.crowbus.dto.member.signup.PassengerDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RoleType;
import com.sizxero.crowbus.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
                List<PassengerReadDTO> dtos = result.stream().map(v ->
                        PassengerReadDTO.builder()
                                .loginId(v.getLoginId())
                                .name(v.getName())
                                .phone(v.getPhone())
                                .favoriteRoute(v.getFavoriteRoute() == null
                                        ? null
                                        : RouteDTO.builder()
                                        .id(v.getFavoriteRoute().getId())
                                        .name(v.getFavoriteRoute().getName())
                                        .routeType(v.getFavoriteRoute().getRouteType().name())
                                        .build())
                                .build()).toList();
                return ResponseEntity.ok().body(
                        ResponseDTO.<PassengerReadDTO>builder()
                                .data(dtos)
                                .build()
                );
            } else if(memberService.findRoleTypeByLoginId(id).equals(RoleType.BUSDRIVER)) {
                Optional<BusDriver> result = memberService.readOneBusDriverByLoginId(id);
                List<BusDriverReadDTO> dtos = result.stream().map(v ->
                        BusDriverReadDTO.builder()
                                .loginId(v.getLoginId())
                                .name(v.getName())
                                .phone(v.getPhone())
                                .license(v.getDriverLicenseNo())
                                .build()).toList();
                return ResponseEntity.ok().body(
                        ResponseDTO.<BusDriverReadDTO>builder()
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

    @GetMapping("/name")
    public ResponseEntity<?> readNameByLoginId(@AuthenticationPrincipal String id) {
        try {
            String result = memberService.readNameByLoginId(id);
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id/{loginId}")
    public ResponseEntity<?> isExistLoginId(@PathVariable String loginId) {
        try {
            boolean result = memberService.existLoginId(loginId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}