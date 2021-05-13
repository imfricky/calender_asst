package com.example.demo.service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSlots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }




}
