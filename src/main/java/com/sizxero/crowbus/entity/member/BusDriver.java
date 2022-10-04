package com.sizxero.crowbus.entity.member;

import javax.persistence.*;

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
@DiscriminatorValue("D")
@SuperBuilder
public class BusDriver extends Member {
    @NotNull
    @Column(name="d_driver_license_no", length = 30)
    private String driverLicenseNo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="d_driver_id")
    private List<Drive> drives = new ArrayList<>();
}