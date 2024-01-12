package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Stamp;

public interface StampRepository extends JpaRepository<Stamp, Long> {
	// employee_idが現在ログインしているユーザーの名前であり、かつend_timeがnullであるレコードを取得
    Stamp findByEmployeeIdAndEndTimeIsNullOrderByStartTimeDesc(String employeeId);
}