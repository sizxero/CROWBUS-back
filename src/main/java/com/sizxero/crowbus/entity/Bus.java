package com.sizxero.crowbus.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Bus {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer capacity;

    @OneToMany
    @JoinColumn(name="bus_id")
    private List<Schedule> schedules = new ArrayList<>();
}
