package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
