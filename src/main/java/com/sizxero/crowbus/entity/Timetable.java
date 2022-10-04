package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Timetable {
    @Id
    @GeneratedValue
    @Column(name="t_id")
    private Long id;
    @NotNull
    @Column(name="t_place", length = 50)
    private String place;
    @Column(name="t_arrival_time")
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;

    @ManyToOne
    @JoinColumn(name="t_route_id")
    private Route route;
}
