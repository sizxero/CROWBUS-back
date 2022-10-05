package com.sizxero.crowbus.dto.drive;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriveDTO {
    private Long id;
    private LocalDate startDay;
    private LocalDate endDay;
    private Long busId;
    private Long driverId;
    private Long routeId;
}
