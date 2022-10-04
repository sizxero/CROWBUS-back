package com.sizxero.crowbus.entity.member;

import javax.persistence.*;

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
@DiscriminatorValue("S")
@SuperBuilder
public class Staff extends Member {
    @NotNull
    @Column(name="s_emp_no", length=20)
    private String empNo;

    @NotNull
    @Column(name="s_department", length=30)
    private String department;
}
