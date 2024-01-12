package com.example.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Stamp;
import com.example.service.StampService;

@Controller
public class PunchController {

    private final StampService stampService;

    @Autowired
    public PunchController(StampService stampService) {
        this.stampService = stampService;
    }

    @PostMapping("/punch/in")
    public String clockIn(Principal principal) {
        // 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();

        // monthに現在の月を設定
        int month = now.getMonthValue();

        // employee_idを現在ログインしているユーザーの名前に設定
        String employeeId = principal.getName();

        // start_timeに現在時刻を設定
        LocalDateTime startTime = now;

        // Stampエンティティを作成してデータベースに保存
        Stamp stamp = new Stamp();
        stamp.setEmployeeId(employeeId);
        stamp.setMonth(month);
        stamp.setStartTime(startTime);

        stampService.saveStamp(stamp);

        return "redirect:/hello";
    }

    @PostMapping("/punch/out")
    public String clockOut(Principal principal) {
        // 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();

        // employee_idが現在ログインしているユーザーの名前であり、かつend_timeがnullであるレコードを取得
        Stamp lastStamp = stampService.getLastStamp(principal.getName());

        if (lastStamp != null) {
            // end_timeに現在時刻を設定してデータベースに保存
            lastStamp.setEndTime(now);
            stampService.saveStamp(lastStamp);
        }

        return "redirect:/hello";
    }
}
