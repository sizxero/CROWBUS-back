package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Bus {
    @Id
    @GeneratedValue
    @Column(name="b_id")
    private Long id;
    @NotNull
    @Column(name="b_busnum", length = 20)
    private String busNum;
    @NotNull
    @Column(name="b_capacity")
    private Integer capacity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="d_bus_id")
    private List<Drive> drives = new ArrayList<>();
}
