package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.bus.BusDTO;
import com.sizxero.crowbus.dto.drive.DriveDTO;
import com.sizxero.crowbus.dto.reservation.ReservationReadDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.dto.seat.SeatDTO;
import com.sizxero.crowbus.dto.seat.SeatUpdateDTO;
import com.sizxero.crowbus.entity.Reservation;
import com.sizxero.crowbus.entity.Seat;
import com.sizxero.crowbus.service.DriveService;
import com.sizxero.crowbus.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @Autowired
    private DriveService driveService;

    @PostMapping
    public ResponseEntity<?> createSeat(@RequestBody SeatDTO dto) {
        try {
            Seat entity = Seat.builder()
                    .date(dto.getDate())
                    .seatNo(dto.getSeatNo())
                    .seatType(dto.getSeatType())
                    .drive(driveService.readOneDrive(dto.getDrive().getId()).get())
                    .build();
            Optional<Seat> result = seatService.createSeat(entity);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<SeatDTO>builder().data(
                            result.stream().map(v ->
                                    SeatDTO.builder()
                                            .id(v.getId())
                                            .date(v.getDate())
                                            .seatNo(v.getSeatNo())
                                            .seatType(v.getSeatType())
                                            .drive(DriveDTO.builder()
                                                    .bus(BusDTO.builder()
                                                            .busNum(v.getDrive().getBus().getBusNum())
                                                            .build())
                                                    .route(RouteDTO.builder()
                                                            .name(v.getDrive().getRoute().getName())
                                                            .routeType(v.getDrive().getRoute().getRouteType().name())
                                                            .build())
                                                    .build())
                                            .build()).toList()
                    ).build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> readSeat(@RequestParam String date, @RequestParam String dvid, @RequestParam(required = false) String rsv) {
        try {
            if(date == null || date.equals("") || date.equals("null") || date.equals("undefined"))
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            List<Seat> result;
            if(rsv == null || rsv.equals(""))
                result = seatService.readSeats(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), dvid);
            else
                result = seatService.readSeatsReservated(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), dvid);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<SeatDTO>builder().data(
                            result.stream().map(v ->
                                    SeatDTO.builder()
                                            .id(v.getId())
                                            .date(v.getDate())
                                            .seatNo(v.getSeatNo())
                                            .seatType(v.getSeatType())
                                            .drive(DriveDTO.builder()
                                                    .bus(BusDTO.builder()
                                                            .busNum(v.getDrive().getBus().getBusNum())
                                                            .build())
                                                    .route(RouteDTO.builder()
                                                            .name(v.getDrive().getRoute().getName())
                                                            .routeType(v.getDrive().getRoute().getRouteType().name())
                                                            .build())
                                                    .build())
                                            .build()).toList()
                    ).build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateSeat(@RequestBody SeatUpdateDTO dto) {
        try {
            Seat newSeat = seatService.readOneSeat(dto.getId()).get();
            newSeat.setSeatType(dto.getSeatType());
            Optional<Seat> result = seatService.update(newSeat);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<SeatDTO>builder()
                            .data(result.stream().map(v ->
                                            SeatDTO.builder()
                                                    .id(v.getId())
                                                    .date(v.getDate())
                                                    .seatNo(v.getSeatNo())
                                                    .seatType(v.getSeatType())
                                                    .drive(DriveDTO.builder()
                                                            .bus(BusDTO.builder()
                                                                    .busNum(v.getDrive().getBus().getBusNum())
                                                                    .build())
                                                            .route(RouteDTO.builder()
                                                                    .name(v.getDrive().getRoute().getName())
                                                                    .routeType(v.getDrive().getRoute().getRouteType().name())
                                                                    .build())
                                                            .build())
                                                    .build())
                                    .toList())
                            .build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
