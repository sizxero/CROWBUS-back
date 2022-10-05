package com.sizxero.crowbus.service;

import com.sizxero.crowbus.entity.Bus;
import com.sizxero.crowbus.repository.BusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public Optional<Bus> createBus(final Bus entity) {
        validate(entity);
        busRepository.save(entity);
        return busRepository.findById(entity.getId());
    }

    public void validate(final Bus entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getBusNum() == null) {
            log.warn("Unknown busnum.");
            throw new RuntimeException("Unknown busnum.");
        }
        if(entity.getCapacity() == null) {
            log.warn("Unknown bus capacity.");
            throw new RuntimeException("Unknown bus capacity.");
        }
    }

    public Optional<Bus> readOneBus(Long id) {
        return busRepository.findById(id);
    }

    public List<Bus> readAllBus() {
        return busRepository.findAll();
    }
}
