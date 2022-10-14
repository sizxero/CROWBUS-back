package com.sizxero.crowbus.dto.drive;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriveCurrDTO {
    private DriveDTO drive;
    private LocalDate date;
    private Integer rsv;
}
