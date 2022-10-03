package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.member.Passenger;
import com.sizxero.crowbus.entity.type.RouteType;
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
public class Route {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private RouteType routeType;

    public Route(String name) {
        this.name = name;
    }

    @OneToMany
    @JoinColumn(name="favorite_route")
    private List<Passenger> passengers = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="route_id")
    private List<Timetable> timetables = new ArrayList<>();
}
