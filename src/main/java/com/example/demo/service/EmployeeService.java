package com.example.demo.service;

import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSlots;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployee();
    Employee saveEmployee(Employee employee);
    Optional<Employee> getEmployee(Long id);

}
