package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.StampWeb;

public interface StampWebRepository extends JpaRepository<StampWeb, Long> {
	// employee_idが現在ログインしているユーザーの名前であり、かつend_timeがnullであるレコードを取得
    StampWeb findByEmployeeIdAndEndTimeIsNullOrderByStartTimeDesc(String employeeId);

	List<StampWeb> findByEmployeeId(String employeeId);
}