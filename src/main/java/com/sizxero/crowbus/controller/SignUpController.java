package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.member.signup.BusDriverDTO;
import com.sizxero.crowbus.dto.member.signup.PassengerDTO;
import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RoleType;
import com.sizxero.crowbus.service.RouteService;
import com.sizxero.crowbus.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("signup")
public class SignUpController {
    @Autowired
    private SignUpService signupService;

    @Autowired
    private RouteService routeService;

    @PostMapping("/passenger")
    public ResponseEntity<?> signupPassenger(@RequestBody PassengerDTO dto){
        try {
            Route fr;
            try {
                fr = routeService.readOneRouteById(dto.getFavoriteRouteId()).get();
            } catch(Exception e) {
                fr = null;
            }
            Passenger entity = Passenger.builder()
                    .id(dto.getId())
                    .roleType(RoleType.PASSENGER)
                    .name(dto.getName())
                    .loginId(dto.getLoginId())
                    .pw(dto.getPw())
                    .phone(dto.getPhone())
                    .favoriteRoute(fr)
                    .build();

            Optional<Passenger> result = signupService.signupPassenger(entity);

            return ResponseEntity.ok()
                    .body(ResponseDTO.<PassengerDTO>builder()
                            .data(result.stream().map(v->
                                    PassengerDTO.builder()
                                            .id(v.getId())
                                            .loginId(v.getLoginId())
                                            .pw(v.getPw())
                                            .name(v.getName())
                                            .phone(v.getPhone())
                                            .favoriteRouteId(v.getFavoriteRoute() != null ? v.getFavoriteRoute().getId(): null)
                                            .build()
                                    ).toList())
                            .build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

    @PostMapping("/busdriver")
    public ResponseEntity<?> signupBusDriver(@RequestBody BusDriverDTO dto){
        try {
            BusDriver entity = BusDriver.builder()
                    .id(dto.getId())
                    .roleType(RoleType.BUSDRIVER)
                    .name(dto.getName())
                    .loginId(dto.getLoginId())
                    .pw(dto.getPw())
                    .phone(dto.getPhone())
                    .driverLicenseNo(dto.getDriverLicenseNo())
                    .build();
            Optional<BusDriver> result = signupService.signupBusDriver(entity);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<BusDriverDTO>builder()
                            .data(result.stream().map(v->
                                    BusDriverDTO.builder()
                                            .id(v.getId())
                                            .loginId(v.getLoginId())
                                            .pw(v.getPw())
                                            .name(v.getName())
                                            .phone(v.getPhone())
                                            .driverLicenseNo(v.getDriverLicenseNo())
                                            .build()
                            ).toList())
                            .build());
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<BusDriverDTO> response =
                    ResponseDTO.<BusDriverDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
