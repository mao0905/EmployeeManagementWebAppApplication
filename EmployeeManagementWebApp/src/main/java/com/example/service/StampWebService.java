package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.StampWeb;
import com.example.repository.StampWebRepository;

@Service
public class StampWebService {

    private final StampWebRepository stampRepository;

    @Autowired
    public StampWebService(StampWebRepository stampRepository) {
        this.stampRepository = stampRepository;
    }

    public List<StampWeb> getAllStamps() {
        return stampRepository.findAll();
    }

    public void saveStamp(StampWeb stamp) {
        stampRepository.save(stamp);
    }

    public StampWeb getStampById(Long id) {
        return stampRepository.findById(id).orElse(null);
    }

    public StampWeb getLastStamp(String employeeId) {
        return stampRepository.findByEmployeeIdAndEndTimeIsNullOrderByStartTimeDesc(employeeId);
    }

	public List<StampWeb> getStampByEmployeeId(String employeeId) {
		return stampRepository.findByEmployeeId(employeeId);
	}
}