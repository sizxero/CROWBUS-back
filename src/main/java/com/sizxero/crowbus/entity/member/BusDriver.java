package com.sizxero.crowbus.entity.member;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.sizxero.crowbus.entity.Member;
import com.sizxero.crowbus.entity.Schedule;
import com.sizxero.crowbus.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("BUSDRIVER")
@Builder
public class BusDriver extends Member {
    private String driverLicenseNo;

    @OneToMany
    @JoinColumn(name="driver_id")
    private List<Schedule> schedules = new ArrayList<>();
}