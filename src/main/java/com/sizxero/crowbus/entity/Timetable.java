package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="t_route_id")
    private Route route;

    public void setRoute(Route route){
        if(this.route != null){
            route.getTimetables().remove(this);
        }
        this.route = route;
        this.route.getTimetables().add(this);
    }
}
