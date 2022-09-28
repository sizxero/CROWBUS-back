package com.sizxero.crowbus.entity;

import com.sizxero.crowbus.entity.type.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Seat {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private Integer seatNo;
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    @OneToMany
    @JoinColumn(name="seat_id")
    private List<Reservation> reservations = new ArrayList<>();
}
