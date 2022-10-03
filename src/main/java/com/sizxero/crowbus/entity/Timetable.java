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
    private Long id;
    @NotNull
    private String place;
    @Temporal(TemporalType.TIME)
    private Date time;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;
}
