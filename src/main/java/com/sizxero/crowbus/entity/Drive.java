package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.member.BusDriver;
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
public class Drive {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate startDay;
    private LocalDate endDay;

    @ManyToOne
    @JoinColumn(name="bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name="driver_id")
    private BusDriver busDriver;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

    @OneToMany
    @JoinColumn(name="drive_id")
    private List<Seat> seats = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="drive_id")
    private List<Holiday> holidays = new ArrayList<>();
}
