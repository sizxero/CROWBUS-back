package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Timetable;
import com.sizxero.crowbus.repository.TimetableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TimetableService {
    @Autowired
    private TimetableRepository repository;

    public Optional<Timetable> createTimetable(final Timetable entity) {
        validate(entity);
        repository.save(entity);
        return repository.findById(entity.getId());
    }

    public void validate(final Timetable entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getPlace() == null) {
            log.warn("Unknown place.");
            throw new RuntimeException("Unknown place name.");
        }
        if(entity.getRoute() == null) {
            log.warn("Unknown route.");
            throw new RuntimeException("Unknown route.");
        }
    }

    public List<Timetable> readTimetableById(Long id) {
        return repository.findTimetablesByRouteId(id);
    }

    public Optional<Timetable> readOneTimetableById(Long id) {
        return repository.findById(id);
    }
}
