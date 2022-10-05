package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.Reservation;
import com.sizxero.crowbus.entity.type.ReservationType;
import com.sizxero.crowbus.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberService memberRepository;

    public Optional<Reservation> createReservation(final Reservation entity) {
        validate(entity);
        reservationRepository.save(entity);
        return reservationRepository.findById(entity.getId());
    }

    public Optional<Reservation> readOneReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> readReservationsByPassenger(String psgId) {
        return reservationRepository.findReservationsByPassenger(memberRepository.readOnePassenger(Long.parseLong(psgId)).get());
    }

    public List<Reservation> readReservationsByReservationTypeAndPassenger(String rt, String psgId) {
        return reservationRepository.findReservationsByReservationTypeAndPassenger(ReservationType.values()[Integer.parseInt(rt)],
                memberRepository.readOnePassenger(Long.parseLong(psgId)).get());
    }

    public Optional<Reservation> update(final Reservation entity) {
        validate(entity);
        final Optional<Reservation> original = reservationRepository.findById(entity.getId());
        original.ifPresent(rv -> {
            rv.setReservationType(entity.getReservationType());
            rv.setSeat(entity.getSeat());
            reservationRepository.save(rv);
        });
        return reservationRepository.findById(entity.getId());
    }
    void validate(final Reservation entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
    }
}
