package com.sizxero.crowbus.dto.drive;

import com.sizxero.crowbus.dto.route.RouteDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriveCreateDTO {
    private Long id;
    private LocalDate startDay;
    private LocalDate endDay;
    private Long busId;
    private Long driverId;
    private Long routeId;
}