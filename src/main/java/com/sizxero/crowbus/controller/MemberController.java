package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.bus.BusDTO;
import com.sizxero.crowbus.dto.drive.DriveDTO;
import com.sizxero.crowbus.dto.member.mypage.BusDriverReadDTO;
import com.sizxero.crowbus.dto.member.mypage.PassengerReadDTO;
import com.sizxero.crowbus.dto.member.signup.BusDriverDTO;
import com.sizxero.crowbus.dto.member.signup.PassengerDTO;
import com.sizxero.crowbus.dto.reservation.ReservationReadDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.dto.seat.SeatDTO;
import com.sizxero.crowbus.dto.timetable.TimetableDTO;
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
                                .reservations(v.getReservations() == null
                                ? null
                                : v.getReservations().stream().map(vv ->
                                    ReservationReadDTO.builder()
                                            .place(TimetableDTO.builder()
                                                    .place(vv.getTimetable().getPlace())
                                                    .arrivalTime(vv.getTimetable() == null
                                                            ? null
                                                            : new java.sql.Timestamp(vv.getTimetable().getArrivalTime().getTime()).toLocalDateTime())
                                                    .build())
                                            .reservationType(vv.getReservationType())
                                            .modifiedTime(vv.getModifiedTime())
                                            .seat(SeatDTO.builder()
                                                    .seatNo(vv.getSeat().getSeatNo())
                                                    .date(vv.getSeat().getDate())
                                                    .drive(DriveDTO.builder()
                                                            .bus(BusDTO.builder()
                                                                    .busNum(vv.getSeat().getDrive().getBus().getBusNum())
                                                                    .build())
                                                            .route(RouteDTO.builder()
                                                                    .name(vv.getSeat().getDrive().getRoute().getName())
                                                                    .routeType(vv.getSeat().getDrive().getRoute().getRouteType().name())
                                                                    .build())
                                                            .build())
                                                    .build())
                                            .build()
                                ).toList())
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
                                .drives(v.getDrives().stream().map(vv ->
                                        DriveDTO.builder()
                                                .startDay(vv.getStartDay())
                                                .endDay(vv.getEndDay())
                                                .route(RouteDTO.builder()
                                                        .name(vv.getRoute().getName())
                                                        .routeType(vv.getRoute().getRouteType().name())
                                                        .build())
                                                .bus(BusDTO.builder()
                                                        .busNum(vv.getBus().getBusNum())
                                                        .build())
                                                .build()).toList())
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
