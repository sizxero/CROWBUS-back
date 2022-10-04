package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findTimetablesByRouteId(Long id);
}
