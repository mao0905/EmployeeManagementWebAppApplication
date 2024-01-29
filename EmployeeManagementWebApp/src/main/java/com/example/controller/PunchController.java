package com.example.controller;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    // 出勤ボタン
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

    // 退勤ボタン
    @PostMapping("/punch/out")
    public String clockOut(Principal principal) {
        // 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();

        // employee_idが現在ログインしているユーザーの名前であり、かつend_timeがnullであるレコードを取得
        Stamp lastStamp = stampService.getLastStamp(principal.getName());

        if (lastStamp != null) {            
            // 稼働時間を計算
            Duration duration = Duration.between(lastStamp.getStartTime(), now);
            int workTime = (int) duration.toMinutes();

            // データベースに保存
            lastStamp.setEndTime(now);
            lastStamp.setWorkTime(workTime);            
            stampService.saveStamp(lastStamp);
        }

        return "redirect:/hello";
    }
  
    // 休憩開始ボタン
    @PostMapping("/break/in")
    public String breakIn(Principal principal) {
        // 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();

        // employee_idが現在ログインしているユーザーの名前であり、かつend_timeがnullであるレコードを取得
        Stamp lastStamp = stampService.getLastStamp(principal.getName());

        if (lastStamp != null) {
            // break_start_time_1に現在時刻を設定してデータベースに保存
        	lastStamp.setBreakStartTime_1(now);
            stampService.saveStamp(lastStamp);
        }

        return "redirect:/hello";
    }
   
    // 休憩終了ボタン
    @PostMapping("/break/out")
    public String breakOut(Principal principal) {
        // 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();

        // employee_idが現在ログインしているユーザーの名前であり、かつend_timeがnullであるレコードを取得
        Stamp lastStamp = stampService.getLastStamp(principal.getName());
        

        if (lastStamp != null) {
            // 休憩時間を計算
            Duration duration = Duration.between(lastStamp.getBreakStartTime_1(), now);
            int breakTime = (int) duration.toMinutes();
            
            // データベースに保存
        	lastStamp.setBreakEndTime_1(now);
        	lastStamp.setBreakTime(breakTime);
            stampService.saveStamp(lastStamp);
        }

        return "redirect:/hello";
    }
    
    // 勤怠ボタン
    @GetMapping("/calendar")
    public String showCalendar(Principal principal, Model model) {
        // 現在ログインしているユーザーのstampリストを取得
        List<Stamp> stampList = stampService.getStampByEmployeeId(principal.getName());

        // モデルにリストを追加
        model.addAttribute("stampList", stampList);

        return "/calendar";
    }
}
