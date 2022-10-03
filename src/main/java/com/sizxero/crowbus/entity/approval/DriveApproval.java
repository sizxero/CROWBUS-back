package com.sizxero.crowbus.entity.approval;

import com.sizxero.crowbus.entity.common.BaseTimeEntity;
import com.sizxero.crowbus.entity.member.BusDriver;
import com.sizxero.crowbus.entity.member.Staff;
import com.sizxero.crowbus.entity.type.ApprovalType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DriveApproval extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private ApprovalType approvalType;

    @ManyToOne
    @JoinColumn(name="staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name="driver_id")
    private BusDriver busDriver;
}
