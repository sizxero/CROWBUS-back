package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate startDay;
    private LocalDate endDay;

    @ManyToOne
    @JoinColumn(name="bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name="timetable_id")
    private Timetable timetable;

    @OneToMany
    @JoinColumn(name="schedule_id")
    private List<Seat> seats = new ArrayList<>();
}
