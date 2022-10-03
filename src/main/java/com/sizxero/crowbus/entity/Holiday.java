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

public class Holiday {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn(name="drive_id")
    private Drive drive;
}
