package com.sizxero.crowbus.dto.route;

import com.sizxero.crowbus.dto.timetable.TimetableDTO;
import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.Timetable;
import com.sizxero.crowbus.entity.type.RouteType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RouteDTO {
    private Long id;
    private String name;
    private String routeType;
    private List<TimetableDTO> timetables;
}
