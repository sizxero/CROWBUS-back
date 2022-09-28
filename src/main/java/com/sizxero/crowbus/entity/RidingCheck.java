package com.sizxero.crowbus.entity;

import javax.persistence.*;

import com.sizxero.crowbus.entity.common.BaseTimeEntity;
import com.sizxero.crowbus.entity.type.RidingCheckType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RidingCheck extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private RidingCheckType ridingCheckType;

    @OneToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;
}
