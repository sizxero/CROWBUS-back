package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Drive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DriveRepository extends JpaRepository<Drive, Long> {
    List<Drive> findDrivesByBusDriverId(Long id);

    @Query("select d from Drive d where :now between d.startDay and d.endDay")
    List<Drive> findDrivesByDate(LocalDate now);

    @Query("select d.id from Drive d where d.route.id=:rid and :now between d.startDay and d.endDay")
    Long findDriveIdByRouteIdAndDate(Long rid, LocalDate now);
}
