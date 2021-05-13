package com.example.demo.controller;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void getAllEmployeeTest(){
        when(employeeRepository.findAll()).thenReturn(Stream
                .of(new Employee(101L,"Test1","Test1@xyz"), new Employee(102L,"Test2","Test2@xyz")).collect(Collectors.toList()));
        assertEquals(2,employeeService.getAllEmployee().size());
    }

    @Test
    public void saveEmployeeTest(){
        Employee employee = new Employee(101L,"Test1","Test1@xyz");
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee,employeeService.saveEmployee(employee));
    }

    @Test
    public void getEmployeeTest() {
        Long id = 101L;
        Optional<Employee> employee = Optional.of(new Employee(101L, "Rahul", "Rahul@xyz"));
        when(employeeRepository.findById(id)).thenReturn(employee);
        assertEquals(employee, employeeService.getEmployee(id));
    }


}