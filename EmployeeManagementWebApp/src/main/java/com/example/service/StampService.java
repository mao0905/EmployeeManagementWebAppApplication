package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Stamp;
import com.example.repository.StampRepository;

@Service
public class StampService {

    private final StampRepository stampRepository;

    @Autowired
    public StampService(StampRepository stampRepository) {
        this.stampRepository = stampRepository;
    }

    public List<Stamp> getAllStamps() {
        return stampRepository.findAll();
    }

    public void saveStamp(Stamp stamp) {
        stampRepository.save(stamp);
    }

    public Stamp getStampById(Long id) {
        return stampRepository.findById(id).orElse(null);
    }

    public Stamp getLastStamp(String employeeId) {
        return stampRepository.findByEmployeeIdAndEndTimeIsNullOrderByStartTimeDesc(employeeId);
    }
}