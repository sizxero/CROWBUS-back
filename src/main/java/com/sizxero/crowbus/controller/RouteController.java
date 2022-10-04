package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.RouteType;
import com.sizxero.crowbus.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("route")
public class RouteController {
    @Autowired
    private RouteService service;

    @PostMapping
    public ResponseEntity<?> createRoute(@RequestBody RouteDTO requestDto) {
        try {
            log.info(requestDto.toString());
            Route entity = RouteDTO.toEntity(requestDto);
            log.info(entity.toString());
            Optional<Route> result = service.createRoute(entity);
            List<RouteDTO> dtos =
                    result.stream().map(RouteDTO::new).collect(Collectors.toList());
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
    public ResponseEntity<?> readRoutes(@RequestParam(required = false) String type) {
        try {
            List<Route> result;
            if(type == null || type.equals(""))
                result = service.readAllRoute();
            else {
                RouteType rt = RouteType.values()[Integer.parseInt(type)];
                result = service.readRoutesByRouteType(rt);
            }
            List<RouteDTO> dtos =
                    result.stream().map(RouteDTO::new).collect(Collectors.toList());
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
            Optional<Route> result = service.readOneRouteById(Long.parseLong(id));
            List<RouteDTO> dtos =
                    result.stream().map(RouteDTO::new).collect(Collectors.toList());
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
}
