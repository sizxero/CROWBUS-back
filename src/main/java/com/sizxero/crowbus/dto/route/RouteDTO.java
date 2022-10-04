package com.sizxero.crowbus.dto.route;

import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.RouteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RouteDTO {
    private Long id;
    private String name;
    private String routeType;

    public RouteDTO(final Route entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.routeType = RouteType.values()[entity.getRouteType().ordinal()].name();
    }

    public static Route toEntity(final RouteDTO dto) {
        return Route.builder()
                .name(dto.getName())
                .routeType(RouteType.valueOf(dto.getRouteType()))
                .build();
    }
}
