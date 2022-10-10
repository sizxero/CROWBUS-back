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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public ResponseEntity<?> createRoute(@RequestBody RouteDTO requestDto) {
        try {
            log.info(requestDto.toString());
            Route entity = Route.builder()
                    .id(requestDto.getId())
                    .name(requestDto.getName())
                    .routeType(RouteType.valueOf(requestDto.getRouteType()))
                    .build();
            log.info(entity.toString());
            Optional<Route> result = routeService.createRoute(entity);
            List<RouteDTO> dtos =
                    result.stream().map(v ->
                            RouteDTO.builder()
                                    .id(v.getId())
                                    .name(v.getName())
                                    .routeType(v.getRouteType().name())
                                    .build()
                    ).collect(Collectors.toList());
            ResponseDTO<RouteDTO> response = ResponseDTO.<RouteDTO>builder().data(dtos).build();
            log.info("response dto ok");
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<RouteDTO> response =
                    ResponseDTO.<RouteDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> readAllRoutes(@RequestParam(required = false) String type) {
        try {
            List<Route> result;
            if(type == null || type.equals(""))
                result = routeService.readAllRoute();
            else {
                RouteType rt = RouteType.values()[Integer.parseInt(type)];
                result = routeService.readRoutesByRouteType(rt);
            }
            List<RouteDTO> dtos =
                    result.stream().map(v ->
                        RouteDTO.builder()
                            .id(v.getId())
                            .name(v.getName())
                            .routeType(v.getRouteType().name())
                            .timetables(timetableService.readTimetableById(v.getId()).stream().map(
                                    vv ->
                                            TimetableDTO.builder()
                                                    .routeId(vv.getRoute().getId())
                                                    .order(vv.getOrder())
                                                    .arrivalTime(vv.getArrivalTime() != null ? new java.sql.Timestamp(vv.getArrivalTime().getTime()).toLocalDateTime() : null)
                                                    .place(vv.getPlace())
                                                    .timetableId(vv.getId())
                                                    .build()
                            ).toList())
                            .build()).collect(Collectors.toList());
            ResponseDTO<RouteDTO> response = ResponseDTO.<RouteDTO>builder().data(dtos).build();
            log.info("response dto ok");
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<RouteDTO> response =
                    ResponseDTO.<RouteDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readRoute(@PathVariable String id) {
        try {
            Optional<Route> result = routeService.readOneRouteById(Long.parseLong(id));
            List<TimetableDTO> ttdtos = timetableService.readTimetableById(Long.parseLong(id)).stream().map(v ->
                TimetableDTO.builder()
                        .routeId(v.getRoute().getId())
                        .order(v.getOrder())
                        .arrivalTime(v.getArrivalTime() != null ? new java.sql.Timestamp(v.getArrivalTime().getTime()).toLocalDateTime() : null)
                        .place(v.getPlace())
                        .timetableId(v.getId())
                        .build()
            ).collect(Collectors.toList());
            List<RouteDTO> dtos =
                    result.stream().map(v -> {
                        RouteDTO dto = RouteDTO.builder()
                                .id(v.getId())
                                .name(v.getName())
                                .routeType(v.getRouteType().name())
                                .build();
                        dto.setTimetables(ttdtos);
                        return dto;
                    }).collect(Collectors.toList());
            ResponseDTO<RouteDTO> response = ResponseDTO.<RouteDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<RouteDTO> response =
                    ResponseDTO.<RouteDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
