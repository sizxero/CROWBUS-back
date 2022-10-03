package com.sizxero.crowbus.entity;

import com.sizxero.crowbus.entity.type.SeatType;
import com.sun.istack.NotNull;
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
    @NotNull
    private LocalDate date;
    @NotNull
    private Integer seatNo;
    @NotNull
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name="drive_id")
    private Drive drive;

    @OneToMany
    @JoinColumn(name="seat_id")
    private List<Reservation> reservations = new ArrayList<>();
}
