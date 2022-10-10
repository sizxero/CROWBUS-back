package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.dto.timetable.TimetableDTO;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.Timetable;
import com.sizxero.crowbus.entity.type.RouteType;
import com.sizxero.crowbus.service.RouteService;
import com.sizxero.crowbus.service.TimetableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("timetable")
public class TimetableController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public ResponseEntity<?> createTimetable(@RequestBody TimetableDTO requestDto) {
        try {
            Timetable entity = Timetable.builder()
                    .order(requestDto.getOrder())
                    .place(requestDto.getPlace())
                    .arrivalTime(requestDto.getArrivalTime() != null ? java.sql.Timestamp.valueOf(requestDto.getArrivalTime()) : null)
                    .build();
            entity.setRoute(routeService.readOneRouteById(requestDto.getRouteId()).get());
            Optional<Timetable> result = timetableService.createTimetable(entity);
            List<TimetableDTO> dtos =
                    result.stream().map(v ->
                            TimetableDTO.builder()
                                    .timetableId(v.getId())
                                    .place(v.getPlace())
                                    .order(v.getOrder())
                                    .arrivalTime(v.getArrivalTime() != null ? v.getArrivalTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null)
                                    .routeId(v.getRoute().getId())
                                    .build()).collect(Collectors.toList());
            ResponseDTO<TimetableDTO> response = ResponseDTO.<TimetableDTO>builder().data(dtos).build();
            log.info("response dto ok");
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<TimetableDTO> response =
                    ResponseDTO.<TimetableDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> readTimetable(@RequestParam String id) {
        try {
            List<TimetableDTO> dtos = timetableService.readTimetableById(Long.parseLong(id)).stream().map(
                    v -> TimetableDTO.builder()
                            .timetableId(v.getId())
                            .place(v.getPlace())
                            .routeId(v.getRoute().getId())
                            .order(v.getOrder())
                            .arrivalTime(v.getArrivalTime() != null ? new java.sql.Timestamp(v.getArrivalTime().getTime()).toLocalDateTime() : null)
                            .build()).collect(Collectors.toList());
            ResponseDTO<TimetableDTO> response = ResponseDTO.<TimetableDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<String> res = ResponseDTO.<String>builder().error(err).build();
            return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readTimetableEle(@PathVariable String id) {
        try {
            Optional<Timetable> entity = timetableService.readOneTimetableById(Long.parseLong(id));
            List<TimetableDTO> dtos = entity.stream().map(
                    v -> TimetableDTO.builder()
                            .timetableId(v.getId())
                            .place(v.getPlace())
                            .routeId(v.getRoute().getId())
                            .order(v.getOrder())
                            .arrivalTime(v.getArrivalTime() != null ? new java.sql.Timestamp(v.getArrivalTime().getTime()).toLocalDateTime() : null)
                            .build()).collect(Collectors.toList());
            ResponseDTO<TimetableDTO> response = ResponseDTO.<TimetableDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<String> res = ResponseDTO.<String>builder().error(err).build();
            return ResponseEntity.badRequest().body(res);
        }
    }
}
