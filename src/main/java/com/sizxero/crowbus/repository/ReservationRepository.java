package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Reservation;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationsByPassenger(Passenger psg);
    List<Reservation> findReservationsByReservationTypeAndPassenger(ReservationType rt, Passenger psg);
}
