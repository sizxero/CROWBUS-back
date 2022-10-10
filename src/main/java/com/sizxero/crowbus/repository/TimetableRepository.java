package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    @Query("select t from Timetable t where t.route.id=?1 order by t.order")
    List<Timetable> findTimetablesByRouteId(Long id);
}
