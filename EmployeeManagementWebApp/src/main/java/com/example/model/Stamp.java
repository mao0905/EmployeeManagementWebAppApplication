package com.example.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;
    private Integer month;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime breakStartTime1;
    private LocalDateTime breakEndTime1;
    private LocalDateTime breakStartTime2;
    private LocalDateTime breakEndTime2;
    private LocalDateTime breakStartTime3;
    private LocalDateTime breakEndTime3;
    private Integer workTime;
    private Integer breakTime;
    private String note;
    private Integer status;
}
