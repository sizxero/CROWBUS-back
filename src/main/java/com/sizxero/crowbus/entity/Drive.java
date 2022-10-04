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
    @Column(name="d_id")
    private Long id;
    @Column(name="d_start_day")
    private LocalDate startDay;
    @Column(name="d_end_day")
    private LocalDate endDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="d_bus_id")
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="d_driver_id")
    private BusDriver busDriver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="d_route_id")
    private Route route;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="s_drive_id")
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="h_drive_id")
    private List<Holiday> holidays = new ArrayList<>();
}
