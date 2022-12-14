package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.dto.drive.DriveCurrDTO;
import com.sizxero.crowbus.entity.Drive;
import com.sizxero.crowbus.entity.Seat;
import com.sizxero.crowbus.entity.type.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findSeatsByDateAndDrive(LocalDate date, Drive drive);
    List<Seat> findSeatsByDateAndDriveAndSeatType(LocalDate date, Drive drive, SeatType st);
    Integer countSeatByDateAndDriveAndSeatType(LocalDate date, Drive drive, SeatType st);
}
