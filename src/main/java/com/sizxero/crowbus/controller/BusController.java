package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.bus.BusDTO;
import com.sizxero.crowbus.entity.Bus;
import com.sizxero.crowbus.service.BusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("bus")
public class BusController {
    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<?> createBus(@RequestBody BusDTO requestDto) {
        try {
            Bus entity = Bus.builder()
                    .id(requestDto.getId())
                    .busNum(requestDto.getBusNum())
                    .capacity(requestDto.getCapacity())
                    .build();
            Optional<Bus> result = busService.createBus(entity);
            List<BusDTO> dtos = result.stream().map(v ->
                    BusDTO.builder()
                            .id(v.getId())
                            .busNum(v.getBusNum())
                            .capacity(v.getCapacity())
                            .build()).toList();
            return ResponseEntity.ok()
                    .body(ResponseDTO.<BusDTO>builder().data(dtos).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseDTO.builder()
                            .error(e.getMessage())
                            .build());
        }
    }

    @GetMapping
    public ResponseEntity<?> readBus(@RequestParam(required = false) String id) {
        try {
            List<BusDTO> dtos;
            if(id == null || id.equals("")) {
                List<Bus> result = busService.readAllBus();
                dtos = result.stream().map(v -> BusDTO.builder()
                        .id(v.getId())
                        .busNum(v.getBusNum())
                        .capacity(v.getCapacity())
                        .build()).toList();
            } else {
                Optional<Bus> singleResult = busService.readOneBus(Long.parseLong(id));
                dtos = singleResult.stream().map(v -> BusDTO.builder()
                        .id(v.getId())
                        .busNum(v.getBusNum())
                        .capacity(v.getCapacity())
                        .build()).toList();
            }
            return ResponseEntity.ok()
                    .body(ResponseDTO.<BusDTO>builder().data(dtos).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }
}
