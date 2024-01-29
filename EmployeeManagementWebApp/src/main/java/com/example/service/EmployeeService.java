package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}
