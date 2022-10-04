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
    @Column(name="s_id")
    private Long id;
    @NotNull
    @Column(name="s_date")
    private LocalDate date;
    @NotNull
    @Column(name="s_seat_no")
    private Integer seatNo;
    @NotNull
    @Column(name="s_seat_type")
    private SeatType seatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="s_drive_id")
    private Drive drive;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="rv_seat_id")
    private List<Reservation> reservations = new ArrayList<>();
}
