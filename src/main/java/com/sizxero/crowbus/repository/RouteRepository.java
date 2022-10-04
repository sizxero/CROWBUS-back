package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.RouteType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Long> {
    List<Route> findRoutesByRouteType(RouteType routeType);
}
