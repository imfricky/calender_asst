package com.example.demo.service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void shouldReturnTotalNoOfEmployeesFromGetAllEmployee(){
        when(employeeRepository.findAll()).thenReturn(Stream
                .of(new Employee(101L,"Test1","Test1@xyz"), new Employee(102L,"Test2","Test2@xyz")).collect(Collectors.toList()));
        assertEquals(2,employeeService.getAllEmployee().size());
    }

    @Test
    public void shouldReturnTotalNoOfEmployeesFromGetAllEmployeeForNull(){
        when(employeeRepository.findAll()).thenReturn(null);
        assertNull(employeeService.getAllEmployee());
    }

    @Test
    public void shouldReturnOneEmployeeForSaveEmployee(){
        Employee employee = new Employee(101L,"Test1","Test1@xyz");
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee,employeeService.saveEmployee(employee));
    }

    @Test
    public void shoulReturnNullEmployeeForSaveEmployee(){
        Employee employee = new Employee(101L,"Test1","Test1@xyz");
        when(employeeRepository.save(null)).thenReturn(null);
        assertEquals(null,employeeService.saveEmployee(null));
    }

    @Test
    public void shouldReturnEmployeeForGetEmployeeService() {
        Long id = 101L;
        Optional<Employee> employee = Optional.of(new Employee(101L, "Rahul", "Rahul@xyz"));
        when(employeeRepository.findById(id)).thenReturn(employee);
        assertEquals(employee, employeeService.getEmployee(id));
    }

    @Test
    public void shouldReturnNullEmployeeForGetEmployeeService() {
        when(employeeRepository.findById(null)).thenReturn(null);
        assertEquals(null, employeeService.getEmployee(null));
    }




}
