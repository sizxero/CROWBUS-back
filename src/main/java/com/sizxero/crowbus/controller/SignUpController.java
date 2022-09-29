package com.sizxero.crowbus.controller;

import com.sizxero.crowbus.dto.ResponseDTO;
import com.sizxero.crowbus.dto.member.signup.BusDriverDTO;
import com.sizxero.crowbus.dto.member.signup.PassengerDTO;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("signup")
public class SignUpController {
    @Autowired
    private SignUpService service;

    @PostMapping("/passenger")
    public ResponseEntity<?> signupPassenger(@RequestBody PassengerDTO requestDto){
        try {
            Passenger entity = PassengerDTO.toEntity(requestDto);
            Optional<Passenger> result = service.signupPassenger(entity);
            List<PassengerDTO> dtos =
                    result.stream().map(PassengerDTO::new).collect(Collectors.toList());
            ResponseDTO<PassengerDTO> response = ResponseDTO.<PassengerDTO>builder().data(dtos).build();
            log.info("response dto ok");
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<PassengerDTO> response =
                    ResponseDTO.<PassengerDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/busdriver")
    public ResponseEntity<?> signupBusDriver(@RequestBody BusDriverDTO requestDto){
        try {
            BusDriver entity = BusDriverDTO.toEntity(requestDto);
            Optional<BusDriver> result = service.signupBusDriver(entity);
            List<BusDriverDTO> dtos =
                    result.stream().map(BusDriverDTO::new).collect(Collectors.toList());
            ResponseDTO<BusDriverDTO> response = ResponseDTO.<BusDriverDTO>builder().data(dtos).build();
            log.info("response dto ok");
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String err = e.getMessage();
            ResponseDTO<BusDriverDTO> response =
                    ResponseDTO.<BusDriverDTO>builder().error(err).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
