package com.sizxero.crowbus.dto.drive;

import com.sizxero.crowbus.dto.bus.BusDTO;
import com.sizxero.crowbus.dto.route.RouteDTO;
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
    private BusDTO bus;
    private Long driverId;
    private RouteDTO route;
}
