package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.bus.BusDTO;
import com.sizxero.crowbus.dto.drive.DriveDTO;
import com.sizxero.crowbus.dto.reservation.ReservationCreateDTO;
import com.sizxero.crowbus.dto.reservation.ReservationReadDTO;
import com.sizxero.crowbus.dto.reservation.ReservationUpdateDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.dto.seat.SeatDTO;
import com.sizxero.crowbus.dto.timetable.TimetableDTO;
import com.sizxero.crowbus.entity.Reservation;
import com.sizxero.crowbus.entity.Seat;
import com.sizxero.crowbus.entity.type.ReservationType;
import com.sizxero.crowbus.entity.type.SeatType;
import com.sizxero.crowbus.service.MemberService;
import com.sizxero.crowbus.service.ReservationService;
import com.sizxero.crowbus.service.SeatService;
import com.sizxero.crowbus.service.TimetableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public ResponseEntity<?> createReservation(@AuthenticationPrincipal String id, @RequestBody ReservationCreateDTO dto) {
        try {
            Reservation entity = Reservation.builder()
                    .reservationType(ReservationType.탑승예정)
                    .passenger(memberService.readOnePassengerByLoginId(id).get())
                    .seat(seatService.readOneSeat(dto.getSeatId()).get())
                    .timetable(timetableService.readOneTimetableById(dto.getPlaceId()).get())
                    .build();
            Optional<Reservation> result = reservationService.createReservation(entity);
            Seat seat = seatService.readOneSeat(dto.getSeatId()).get();
            seat.setSeatType(SeatType.예약불가);
            seatService.update(seat);
            return ResponseEntity.ok().body(
                    ResponseDTO.<ReservationReadDTO>builder()
                            .data(result.stream().map(v ->
                                    ReservationReadDTO.builder()
                                            .passengerId(v.getPassenger().getId())
                                            .seat(SeatDTO.builder()
                                                    .seatNo(v.getSeat().getSeatNo())
                                                    .date(v.getSeat().getDate())
                                                    .drive(DriveDTO.builder()
                                                            .bus(BusDTO.builder()
                                                                    .busNum(v.getSeat().getDrive().getBus().getBusNum())
                                                                    .build())
                                                            .route(RouteDTO.builder()
                                                                    .name(v.getSeat().getDrive().getRoute().getName())
                                                                    .routeType(v.getSeat().getDrive().getRoute().getRouteType().name())
                                                                    .build())
                                                            .build())
                                                    .build())
                                            .place(TimetableDTO.builder()
                                                    .place(v.getTimetable().getPlace())
                                                    .arrivalTime(v.getTimetable() == null
                                                            ? null
                                                            : new java.sql.Timestamp(v.getTimetable().getArrivalTime().getTime()).toLocalDateTime())
                                                    .build())
                                            .reservationType(v.getReservationType())
                                            .createTime(v.getCreateTime())
                                            .modifiedTime(v.getModifiedTime())
                                            .build()
                            ).toList())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

    @GetMapping
    public ResponseEntity<?> readReservationByMember(@RequestParam String mbId, @RequestParam(required = false) String rvType) {
        try {
            List<Reservation> result;
            if(rvType == null || rvType.equals(""))
                result = reservationService.readReservationsByPassenger(mbId);
            else
                result = reservationService.readReservationsByReservationTypeAndPassenger(rvType, mbId);

            return ResponseEntity.ok().body(
                    ResponseDTO.<ReservationReadDTO>builder()
                            .data(result.stream().map(v ->
                                    ReservationReadDTO.builder()
                                            .passengerId(v.getPassenger().getId())
                                            .seat(SeatDTO.builder()
                                                    .seatNo(v.getSeat().getSeatNo())
                                                    .date(v.getSeat().getDate())
                                                    .drive(DriveDTO.builder()
                                                            .bus(BusDTO.builder()
                                                                    .busNum(v.getSeat().getDrive().getBus().getBusNum())
                                                                    .build())
                                                            .route(RouteDTO.builder()
                                                                    .name(v.getSeat().getDrive().getRoute().getName())
                                                                    .routeType(v.getSeat().getDrive().getRoute().getRouteType().name())
                                                                    .build())
                                                            .build())
                                                    .build())
                                            .place(TimetableDTO.builder()
                                                    .place(v.getTimetable().getPlace())
                                                    .arrivalTime(v.getTimetable() == null
                                                            ? null
                                                            : new java.sql.Timestamp(v.getTimetable().getArrivalTime().getTime()).toLocalDateTime())
                                                    .build())
                                            .reservationType(v.getReservationType())
                                            .createTime(v.getCreateTime())
                                            .modifiedTime(v.getModifiedTime())
                                            .build()
                            ).toList())
                            .build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateReservation(@RequestBody ReservationUpdateDTO dto) {
        try {
            Reservation newRv = Reservation.builder()
                    .id(dto.getRvId())
                    .seat(seatService.readOneSeat(dto.getSeatId()).get())
                    .reservationType(dto.getRvType())
                    .build();
            Optional<Reservation> result = reservationService.update(newRv);
            if(dto.getRvType().equals(ReservationType.예약취소)) {
                Seat seat = seatService.readOneSeat(dto.getSeatId()).get();
                seat.setSeatType(SeatType.예약가능);
                seatService.update(seat);
            }
            reservationService.update(newRv);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<ReservationReadDTO>builder()
                            .data(result.stream().map(v ->
                                            ReservationReadDTO.builder()
                                                    .reservationType(v.getReservationType())
                                                    .seat(SeatDTO.builder()
                                                            .seatNo(v.getSeat().getSeatNo())
                                                            .date(v.getSeat().getDate())
                                                            .drive(DriveDTO.builder()
                                                                    .bus(BusDTO.builder()
                                                                            .busNum(v.getSeat().getDrive().getBus().getBusNum())
                                                                            .build())
                                                                    .route(RouteDTO.builder()
                                                                            .name(v.getSeat().getDrive().getRoute().getName())
                                                                            .routeType(v.getSeat().getDrive().getRoute().getRouteType().name())
                                                                            .build())
                                                                    .build())
                                                            .build())
                                                    .passengerId(v.getPassenger().getId())
                                                    .createTime(v.getCreateTime())
                                                    .modifiedTime(v.getModifiedTime())
                                            .build())
                                    .toList())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }
}
