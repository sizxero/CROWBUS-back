package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.seat.SeatDTO;
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
                    .drive(driveService.readOneDrive(dto.getDriveId()).get())
                    .build();
            Optional<Seat> result = seatService.createSeat(entity);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<SeatDTO>builder().data(
                            result.stream().map(v ->
                                    SeatDTO.builder()
                                            .date(v.getDate())
                                            .seatNo(v.getSeatNo())
                                            .seatType(v.getSeatType())
                                            .driveId(v.getDrive().getId())
                                            .build()).toList()
                    ).build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> readSeat(@RequestParam String date, @RequestParam String dvid) {
        try {
            log.info(LocalDate.parse(date, DateTimeFormatter.ISO_DATE).toString());
            List<Seat> result = seatService.readSeats(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), dvid);
            log.info(String.valueOf(result.size()));
            return ResponseEntity.ok()
                    .body(ResponseDTO.<SeatDTO>builder().data(
                            result.stream().map(v ->
                                    SeatDTO.builder()
                                            .date(v.getDate())
                                            .seatNo(v.getSeatNo())
                                            .seatType(v.getSeatType())
                                            .driveId(v.getDrive().getId())
                                            .build()).toList()
                    ).build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
