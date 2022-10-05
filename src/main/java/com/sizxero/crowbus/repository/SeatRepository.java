package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Drive;
import com.sizxero.crowbus.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findSeatsByDateAndDrive(LocalDate date, Drive drive);
}
