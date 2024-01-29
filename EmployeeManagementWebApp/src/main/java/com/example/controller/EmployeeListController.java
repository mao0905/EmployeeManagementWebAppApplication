package com.example.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@Controller
public class EmployeeListController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeListController(EmployeeService employeeService) {
		this.employeeService = employeeService;
    }

	// 削除ボタン
	@PostMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") String employeeId, Model model) {
        // 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();
        
		// 該当する社員IDのオブジェクトを取得
		Employee employee = employeeService.getEmployeeById(employeeId);
		
		// delete_timeに現在時刻を設定してデータベースに保存
		employee.setDeleteTime(now);
		employeeService.saveEmployee(employee);

    	// 社員一覧のリストを再取得してモデルにセット
    	List<Employee> employeeList = employeeService.getAllEmployees();
    	model.addAttribute("employeeList", employeeList);
		
		return "/employee_list";		
	}
	
	// 戻るボタン
	@GetMapping("/hello/employee/list")
	public String showHello(Principal principal) {
	    return "/hello";
	}

}
