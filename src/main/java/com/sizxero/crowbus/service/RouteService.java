package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Route;
import com.sizxero.crowbus.entity.type.RouteType;
import com.sizxero.crowbus.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RouteService {
    @Autowired
    private RouteRepository repository;

    public Optional<Route> createRoute(final Route entity) {
        validate(entity);
        repository.save(entity);
        return repository.findById(entity.getId());
    }

    public void validate(final Route entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getName() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown route name.");
        }
        if(entity.getRouteType() == null) {
            log.warn("Unknown route type.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<Route> readAllRoute() {
        return repository.findAll();
    }

    public List<Route> readRoutesByRouteType(RouteType routeType) {
        return repository.findRoutesByRouteType(routeType);
    }

    public Optional<Route> readOneRouteById(Long id) {
        return repository.findById(id);
    }
}
