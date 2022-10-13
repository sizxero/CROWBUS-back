package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.bus.BusDTO;
import com.sizxero.crowbus.dto.drive.DriveCreateDTO;
import com.sizxero.crowbus.dto.drive.DriveDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.dto.timetable.TimetableDTO;
import com.sizxero.crowbus.entity.Drive;
import com.sizxero.crowbus.entity.Seat;
import com.sizxero.crowbus.entity.type.SeatType;
import com.sizxero.crowbus.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("drive")
public class DriveController {
    @Autowired
    private DriveService driveService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;
    
    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<?> createDrive(@RequestBody DriveCreateDTO dto) {
        try {
            Drive entity = Drive.builder()
                    .id(dto.getId())
                    .startDay(dto.getStartDay())
                    .endDay(dto.getEndDay())
                    .busDriver(memberService.readOneBusDriver(dto.getDriverId()).get())
                    .bus(busService.readOneBus(dto.getBusId()).get())
                    .route(routeService.readOneRouteById(dto.getRouteId()).get())
                    .build();
            Optional<Drive> result = driveService.createDrive(entity);
            LocalDate start = dto.getStartDay();
            LocalDate end = dto.getEndDay().plusDays(1);
            for (LocalDate d = start; d.isBefore(end); d = d.plusDays(1)) {
                for(int i = 1; i<= 45; i++) {
                    Seat seatEntity = Seat.builder()
                            .date(d)
                            .seatNo(i)
                            .seatType(SeatType.예약가능)
                            .drive(entity)
                            .build();
                    seatService.createSeat(seatEntity);
                }
            }
            List<DriveDTO> dtos = result.stream().map(v ->
                    DriveDTO.builder()
                            .id(v.getId())
                            .startDay(v.getStartDay())
                            .endDay(v.getEndDay())
                            .driverId(v.getBusDriver().getId())
                            .route(RouteDTO.builder()
                                    .id(v.getRoute().getId())
                                    .name(v.getRoute().getName())
                                    .routeType(v.getRoute().getRouteType().name())
                                    .timetables(v.getRoute().getTimetables().stream()
                                            .map(vv -> TimetableDTO.builder()
                                                    .place(vv.getPlace())
                                                    .arrivalTime(
                                                            vv.getArrivalTime() != null
                                                            ? new Timestamp(vv.getArrivalTime().getTime())
                                                                    .toLocalDateTime()
                                                                    : null
                                                    )
                                                    .order(vv.getOrder())
                                                    .build()).toList())
                                    .build())
                            .bus(BusDTO.builder()
                                    .id(v.getBus().getId())
                                    .busNum(v.getBus().getBusNum())
                                    .build())
                            .build()).toList();
            return ResponseEntity.ok()
                    .body(ResponseDTO.<DriveDTO>builder().data(dtos).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> readDrive(@RequestParam(required = false) String id,
                                       @RequestParam(required = false) String dvid,
                                       @RequestParam(required = false) String date) {
        try {
            if(date == null || date.equals("") || date.equals("null") || date.equals("undefined"))
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));;

            List<DriveDTO> dtos;
            if(id == null || id.equals("")) {
                List<Drive> multiResult;
                if(dvid == null || dvid.equals(""))
                    multiResult = driveService.readDrivesByDate(date);
                else
                    multiResult = driveService.readDrivesByDriverId(Long.parseLong(dvid));

                dtos = multiResult.stream().map(v ->
                        DriveDTO.builder()
                                .id(v.getId())
                                .startDay(v.getStartDay())
                                .endDay(v.getEndDay())
                                .driverId(v.getBusDriver().getId())
                                .route(RouteDTO.builder()
                                        .id(v.getRoute().getId())
                                        .name(v.getRoute().getName())
                                        .routeType(v.getRoute().getRouteType().name())
                                        .timetables(v.getRoute().getTimetables().stream()
                                                .map(vv -> TimetableDTO.builder()
                                                        .place(vv.getPlace())
                                                        .arrivalTime(
                                                                vv.getArrivalTime() != null
                                                                ?new java.sql.Timestamp(vv.getArrivalTime().getTime())
                                                                        .toLocalDateTime() : null
                                                                )
                                                        .order(vv.getOrder())
                                                        .build()).toList())
                                        .build())
                                .bus(BusDTO.builder()
                                        .id(v.getBus().getId())
                                        .busNum(v.getBus().getBusNum())
                                        .build())
                                .build()).toList();
            } else {
                Optional<Drive> singleResult = driveService.readOneDrive(Long.parseLong(id));
                dtos = singleResult.stream().map(v ->
                        DriveDTO.builder()
                                .id(v.getId())
                                .startDay(v.getStartDay())
                                .endDay(v.getEndDay())
                                .driverId(v.getBusDriver().getId())
                                .route(RouteDTO.builder()
                                        .id(v.getRoute().getId())
                                        .name(v.getRoute().getName())
                                        .routeType(v.getRoute().getRouteType().name())
                                        .timetables(v.getRoute().getTimetables().stream()
                                                .map(vv -> TimetableDTO.builder()
                                                        .place(vv.getPlace())
                                                        .arrivalTime(
                                                                new java.sql.Timestamp(vv.getArrivalTime().getTime())
                                                                        .toLocalDateTime()
                                                        )
                                                        .order(vv.getOrder())
                                                        .build()).toList())
                                        .build())
                                .bus(BusDTO.builder()
                                        .id(v.getBus().getId())
                                        .busNum(v.getBus().getBusNum())
                                        .build())
                                .build()).toList();
            }
            return ResponseEntity.ok()
                    .body(ResponseDTO.<DriveDTO>builder().data(dtos).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find")
    public Long readDriveIdWithRouteIdAndDate(@RequestParam String rid, @RequestParam String date) {
        if(date == null || date.equals("") || date.equals("null") || date.equals("undefined"))
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));;
        return driveService.readDriveIdByRouteIdAndDate(rid, date);
    }
}
