package com.sizxero.crowbus.repository;

import com.sizxero.crowbus.entity.Drive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriveRepository extends JpaRepository<Drive, Long> {
    List<Drive> findDrivesByBusDriverId(Long id);
}
