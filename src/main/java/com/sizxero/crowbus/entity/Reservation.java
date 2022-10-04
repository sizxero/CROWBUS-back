package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.common.BaseTimeEntity;
import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.ReservationType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name="rv_id")
    private Long id;
    @NotNull
    @Column(name="rv_reservation_type")
    private ReservationType reservationType;

    @ManyToOne
    @JoinColumn(name="rv_passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name="rv_seat_id")
    private Seat seat;
}
