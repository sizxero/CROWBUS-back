package com.sizxero.crowbus.entity.member;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.sizxero.crowbus.entity.Drive;
import com.sizxero.crowbus.entity.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("B")
@SuperBuilder
public class BusDriver extends Member {
    @NotNull
    private String driverLicenseNo;

    @OneToMany
    @JoinColumn(name="driver_id")
    private List<Drive> drives = new ArrayList<>();
}