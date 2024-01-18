package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Stamp;
import com.example.service.StampService;

@RestController
@RequestMapping("/api/stamps")
public class StampController {

    private final StampService stampService;

    @Autowired
    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @GetMapping
    public List<Stamp> getAllStamps() {
        return stampService.getAllStamps();
    }

    @PostMapping
    public void saveStamp(@RequestBody Stamp stamp) {
        stampService.saveStamp(stamp);
    }

    @GetMapping("/{id}")
    public Stamp getStampById(@PathVariable Long id) {
        return stampService.getStampById(id);
    }
}
