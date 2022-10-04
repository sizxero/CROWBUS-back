package com.sizxero.crowbus.dto.timetable;

import com.sizxero.crowbus.dto.route.RouteDTO;
import com.sizxero.crowbus.entity.Timetable;
import com.sizxero.crowbus.service.RouteService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TimetableDTO {
    private Long timetableId;
    private String place;
    private LocalDateTime arrivalTime;
    private Long routeId;
}
