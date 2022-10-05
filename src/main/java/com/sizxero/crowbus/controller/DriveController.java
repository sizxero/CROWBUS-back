package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.drive.DriveDTO;
import com.sizxero.crowbus.entity.Drive;
import com.sizxero.crowbus.service.BusService;
import com.sizxero.crowbus.service.DriveService;
import com.sizxero.crowbus.service.MemberService;
import com.sizxero.crowbus.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> createDrive(@RequestBody DriveDTO dto) {
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
            List<DriveDTO> dtos = result.stream().map(v ->
                    DriveDTO.builder()
                            .id(v.getId())
                            .startDay(v.getStartDay())
                            .endDay(v.getEndDay())
                            .driverId(v.getBusDriver().getId())
                            .routeId(v.getRoute().getId())
                            .busId(v.getBus().getId())
                            .build()).toList();
            return ResponseEntity.ok()
                    .body(ResponseDTO.<DriveDTO>builder().data(dtos).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> readDrive(@RequestParam(required = false) String id, @RequestParam(required = false) String dvid) {
        try {
            List<DriveDTO> dtos;
            if(id == null || id.equals("")) {
                List<Drive> multiResult;
                if(dvid == null || dvid.equals(""))
                    multiResult = driveService.readAllDrive();
                else
                    multiResult = driveService.readDrivesByDriverId(Long.parseLong(dvid));

                dtos = multiResult.stream().map(v ->
                        DriveDTO.builder()
                                .id(v.getId())
                                .startDay(v.getStartDay())
                                .endDay(v.getEndDay())
                                .driverId(v.getBusDriver().getId())
                                .routeId(v.getRoute().getId())
                                .busId(v.getBus().getId())
                                .build()).toList();
            } else {
                Optional<Drive> singleResult = driveService.readOneDrive(Long.parseLong(id));
                dtos = singleResult.stream().map(v ->
                        DriveDTO.builder()
                                .id(v.getId())
                                .startDay(v.getStartDay())
                                .endDay(v.getEndDay())
                                .driverId(v.getBusDriver().getId())
                                .routeId(v.getRoute().getId())
                                .busId(v.getBus().getId())
                                .build()).toList();
            }
            return ResponseEntity.ok()
                    .body(ResponseDTO.<DriveDTO>builder().data(dtos).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
