package com.example.demo.controller;

import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value= "/employee-management")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
     List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping("/employees")
     Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
        //return employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
     Optional<Employee> getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/book-meeting/{id}")
     Optional<Employee> bookMeeting(@RequestBody final Calender calender, @PathVariable Long id){
        Optional<Employee> employee = employeeService.getEmployee(id);
        calender.setCid(id);
        Employee employee1 = new Employee();
        if(employee.isPresent()){
            employee1 = employee.get();
            List<Calender> calender1 = new ArrayList<>();
            calender1 = employee1.getCalender();
            calender1.add(calender);
            employee1.setCalender(calender1);
        }
        employeeService.saveEmployee(employee1);

        return employeeService.getEmployee(id);
    }

}
