package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.type.RouteType;
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
    private String name;
    private RouteType routeType;
    @Temporal(TemporalType.TIME)
    private Date departureTime;

    @OneToMany
    @JoinColumn(name="route_id")
    private List<Timetable> timetables = new ArrayList<>();
}
