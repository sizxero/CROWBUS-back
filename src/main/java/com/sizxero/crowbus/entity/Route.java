package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RouteType;
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
public class Route {
    @Id
    @GeneratedValue
    @Column(name="rt_id")
    private Long id;
    @NotNull
    @Column(name="rt_name", length = 50)
    private String name;
    @NotNull
    @Column(name="rt_route_type", length = 50)
    private RouteType routeType;

    public Route(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="p_favorite_route")
    private List<Passenger> passengers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="t_route_id")
    private List<Timetable> timetables = new ArrayList<>();
}
