package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Drive;
import com.sizxero.crowbus.entity.Seat;
import com.sizxero.crowbus.repository.DriveRepository;
import com.sizxero.crowbus.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private DriveRepository driveRepository;

    public Optional<Seat> createSeat(final Seat entity) {
        validate(entity);
        seatRepository.save(entity);
        return seatRepository.findById(entity.getId());
    }

    void validate(final Seat entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getDate() == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getSeatNo() == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getDrive().getId() == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
    }

    public List<Seat> readSeats(LocalDate date, String id) {
        Drive drive = driveRepository.findById(Long.parseLong(id)).get();
        return seatRepository.findSeatsByDateAndDrive(date, drive);
    }
}
