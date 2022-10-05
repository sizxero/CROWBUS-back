package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Drive;
import com.sizxero.crowbus.repository.DriveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DriveService {
    @Autowired
    private DriveRepository driveRepository;

    public Optional<Drive> createDrive(final Drive entity) {
        validate(entity);
        driveRepository.save(entity);
        return driveRepository.findById(entity.getId());
    }

    public void validate(final Drive entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
    }

    public Optional<Drive> readOneDrive(Long id) {
        return driveRepository.findById(id);
    }

    public List<Drive> readDrivesByDriverId(Long driverId) {
        return driveRepository.findDrivesByBusDriverId(driverId);
    }

    public List<Drive> readAllDrive() {
        return driveRepository.findAll();
    }
}
